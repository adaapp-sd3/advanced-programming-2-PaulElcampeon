package com.paul.farm.tourism;

import com.paul.farm.enums.FieldType;
import com.paul.farm.models.fields.PettingFarmField;
import com.paul.farm.services.interfaces.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
public class TourismEngine {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private FarmService farmService;

    @Autowired
    private TourismRevenue tourismRevenue;

    private HashMap<String, Integer> farmAndNoOfVisitors = new HashMap<>();

    @Scheduled(fixedDelay = 500L)
    public void process() {
        Tourist.process(farmAndNoOfVisitors);
        farmService.getAllOnlineFarms().stream().forEach(farm -> {
            if (farm.containsFieldWithAnimals(FieldType.PETTINGFARM)) {
                tourismRevenue.addFarmWallet(farm);
                farmAndNoOfVisitors.putIfAbsent(farm.getFarmName(), 0);
                farm.getFields().stream().forEach(field -> {
                    if (field instanceof PettingFarmField && field.getNoOfAnimals() > 0) {
                        Tourist.getAvailableTouristList().forEach(tourist -> {
                            if (ThreadLocalRandom.current().nextDouble() <= 0.2 && farmAndNoOfVisitors.get(farm.getFarmName()) < 100) {//random chance visitor will go to farm
                                tourist.setFarmName(farm.getFarmName());
                                tourist.setTimeArrived(System.currentTimeMillis());
                                tourist.setField(field);
                                field.addToField(tourist);
                                tourismRevenue.addMoneyToFarmWallet(tourist);
                                farmAndNoOfVisitors.put(farm.getFarmName(), farmAndNoOfVisitors.get(farm.getFarmName()) + 1);
                            }
                        });
                    }
                });
                simpMessagingTemplate.convertAndSend("/topic/farm/tourists/" + farm.getFarmName(), Tourist.getUnavailableTourists().stream().filter(tourist -> tourist.getFarmName().equals(farm.getFarmName())).collect(Collectors.toList()));
            } else {
                boolean present = farmAndNoOfVisitors.containsKey(farm.getFarmName());
                if (present) {
                    farmAndNoOfVisitors.remove(farm.getFarmName());
                    tourismRevenue.removeFarmWalletIfPresent(farm);
                }
                simpMessagingTemplate.convertAndSend("/topic/farm/tourists/" + farm.getFarmName(), Collections.EMPTY_LIST);
            }
        });
    }
}
