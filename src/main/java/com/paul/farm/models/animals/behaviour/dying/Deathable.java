package com.paul.farm.models.animals.behaviour.dying;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import lombok.Data;

@Data
public class Deathable {

    public void checkIfDead(Farm farm, Animal animal) {
        if (animal.getHungerLevel() <= 0) {
            animal.reduceNumber(farm);
            animal.setDead(true);
        }
    }
}
