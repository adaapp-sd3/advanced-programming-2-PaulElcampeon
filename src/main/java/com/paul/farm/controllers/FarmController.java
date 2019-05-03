package com.paul.farm.controllers;

import com.paul.farm.dtos.FarmModificationRequestDto;
import com.paul.farm.dtos.MoveAnimalDto;
import com.paul.farm.dtos.PlantSeedRequestDto;
import com.paul.farm.farmModification.FarmModification;
import com.paul.farm.models.Farm;
import com.paul.farm.services.interfaces.FarmService;
import com.paul.farm.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FarmController {

    @Autowired
    private FarmModification farmModification;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private FarmService farmService;

    private Logger logger = Logger.getLogger(FarmController.class.getName());

    @RequestMapping(value = "/farm/add/petting-farm", method = RequestMethod.POST)
    public Farm addPettingFarm(@RequestBody FarmModificationRequestDto farmModificationRequestDto) {
        return farmModification.addPettingFarm(farmModificationRequestDto);
    }

    @RequestMapping(value = "/farm/add/grazzing-field", method = RequestMethod.POST)
    public Farm addGrazzingField(@RequestBody FarmModificationRequestDto farmModificationRequestDto) {
        return farmModification.addGrazingField(farmModificationRequestDto);
    }

    @RequestMapping(value = "/farm/add/growing-field", method = RequestMethod.POST)
    public Farm addGrowingField(@RequestBody FarmModificationRequestDto farmModificationRequestDto) {
        return farmModification.addGrowingField(farmModificationRequestDto);
    }

    @RequestMapping(value = "/farm/plant/seed", method = RequestMethod.POST)
    public void plantSeed(@RequestBody PlantSeedRequestDto plantSeedRequestDto) {
        boolean result = farmModification.addSeed(plantSeedRequestDto);
        messageUtil.sendMessage(plantSeedRequestDto.getFarmName(), "Sorry could not plant that seed", result);
    }

    @RequestMapping(value = "/farm/move/animal", method = RequestMethod.POST)
    public void moveAnimalToGrazzingField(@RequestBody MoveAnimalDto moveAnimalDto) {
        boolean result = farmModification.moveAnimal(moveAnimalDto);
        messageUtil.sendMessage(moveAnimalDto.getFarmName(),
                "Sorry could not move your animal to that field due to that type of field not existing or the number of animals in that field exceeds the limit or you do not have that animal",
                result);
    }

    @MessageMapping(value = "/farm/get/{farmName}")
    @SendTo(value="/topic/farm/{farmName}")
    public Farm getFarm(@DestinationVariable String farmName) {
        logger.log(Level.INFO, String.format("%S made a request for their farm", farmName));
        return farmService.getFarm(farmName);
    }

    public void sendData(Farm farm) {
        simpMessagingTemplate.convertAndSend("/topic/farm/"+farm.getFarmName(), farm);
    }

//    @MessageMapping(value = "/farm/get")
//    public Farm getFarm(Principal principal) {
//        return farmService.getFarm(principal.getName());
//    }

//    @MessageMapping(value = "/farm/get")
//    public void getFarm(Principal principal) {
//        simpMessagingTemplate.convertAndSend("/topic/farm/" + principal, farmService.getFarm(principal.getName()));
//    }
}
