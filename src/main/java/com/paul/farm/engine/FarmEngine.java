package com.paul.farm.engine;

import com.paul.farm.enums.FieldType;
import com.paul.farm.models.Farm;
import com.paul.farm.tourismBank.TourismBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FarmEngine {

    @Autowired
    private TourismBank tourismBank;

    public void process(Farm farm) {
        farm.setCrops(farm.getCrops().stream().map(crop -> {
            crop.getHarvestable().harvest(farm, crop);
            return crop;
        }).filter(crop -> !crop.isBlossomed()).collect(Collectors.toList()));

        //this should get rid of all dead animals in animal list, we need to think of what to do incase the animal is ready for mature and is also dead because of hunger
        farm.setAnimals(farm.getAnimals().stream().map(animal -> {
            animal.getMovable().move(farm, animal);
            animal.getGrazable().graze(farm, animal);
            animal.getMaintainable().maintain(farm, animal);
            animal.getMaturable().mature(farm, animal);
            animal.getDeathable().checkIfDead(farm, animal);
            return animal;
        }).filter(animal -> !animal.isDead()).collect(Collectors.toList()));

        if (farm.containsFieldWithAnimals(FieldType.PETTINGFARM)) {
            tourismBank.addFarmWallet(farm);
            tourismBank.collectMoneyFromWallet(farm);
        }

        farm.updateAnimalCount();
    }
}
