package com.paul.farm.models.animals.behaviour.maturity;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.utils.AnimalUtil;

public class SheepMaturable implements Maturable {
    @Override
    public void mature(Farm farm, Animal animal) {
        if (System.currentTimeMillis() - animal.getBirthDate() >= AnimalUtil.getAnimalFullMature.get("sheep")) {
            farm.setTotalBeef(farm.getTotalBeef() + 1);
            animal.setDead(true);
            farm.setTotalSheep(farm.getTotalSheep() - 1);
            farm.getFields().get(animal.getFieldIndex()).setNoOfAnimals(farm.getFields().get(animal.getFieldIndex()).getNoOfAnimals() - 1);
        }
    }
}
