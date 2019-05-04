package com.paul.farm.models.animals.behaviour.graze;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import lombok.Data;

@Data
public class CattleGrazable implements Grazable {

    @Override
    public void graze(Farm farm, Animal animal) {
        if (farm.getTotalStraw() > 0) {
            animal.setHungerLevel(animal.getHungerLevel() + 1);
            farm.setTotalStraw(farm.getTotalStraw() - 0.01);
        } else {
            animal.setHungerLevel(animal.getHungerLevel() - 0.02);
        }
    }
}
