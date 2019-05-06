package com.paul.farm.services;

import com.paul.farm.controllers.FarmController;
import com.paul.farm.engine.FarmEngine;
import com.paul.farm.models.Farm;
import com.paul.farm.repositories.FarmRepository;
import com.paul.farm.services.interfaces.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmEngine farmEngine;

    @Autowired
    private FarmController farmController;

    @Override
    public void renameFarm(String id, String newFarmName) {
        Farm farm = getFarm(id);
        farm.setFarmName(newFarmName);
        updateFarm(farm);
    }

    @Override
    public void deleteFarm(String id) {
        farmRepository.deleteById(id);
    }

    @Override
    public void createFarm(String id) {
        farmRepository.insert(new Farm(id));
    }

    @Override
    public void updateFarm(Farm farm) {
        farmRepository.save(farm);
    }

    @Override
    public Farm getFarm(String id) {
        Farm farm = farmRepository.findById(id).orElseThrow(NoSuchElementException::new);
        farm.setOnline(true);
        updateFarm(farm);
        return farm;
    }


    @Scheduled(fixedDelay = 200, initialDelay = 60000)
    @Override
    public void processFarm() {
        farmRepository.findByOnline(true).stream().forEach(farm -> {
//            System.out.println(String.format("%s is online and we are processing their farm", farm.getFarmName()));
            farmEngine.process(farm);
            farmController.sendData(farm);
            updateFarm(farm);
            //possibly check last time farm was ediited before we edit it
        });
    }

    @Override
    public void setOffline(String id) {
        Farm farm = getFarm(id);
        farm.setOnline(false);
        updateFarm(farm);
    }

    @Override
    public List<Farm> getAllOnlineFarms() {
        return farmRepository.findByOnline(true);
    }
}
