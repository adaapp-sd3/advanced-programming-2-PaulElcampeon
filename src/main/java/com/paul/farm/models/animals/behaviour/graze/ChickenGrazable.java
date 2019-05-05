package com.paul.farm.models.animals.behaviour.graze;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import lombok.Data;

@Data
public class ChickenGrazable implements Grazable {

    @Override
    public void graze(Farm farm, Animal animal) {
        if (farm.getTotalCorn() > 0) {
            if (animal.getHungerLevel() < 100) {
                animal.setHungerLevel(animal.getHungerLevel() + 1);
            }
            farm.setTotalCorn(farm.getTotalCorn() - 0.02);
        } else {
            animal.setHungerLevel(animal.getHungerLevel() - 0.02);
        }
    }
}
