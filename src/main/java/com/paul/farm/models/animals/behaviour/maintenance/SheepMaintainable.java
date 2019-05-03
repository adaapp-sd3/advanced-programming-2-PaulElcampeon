package com.paul.farm.models.animals.behaviour.maintenance;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.utils.AnimalUtil;


public class SheepMaintainable implements Maintainable {
    @Override
    public void maintain(Farm farm, Animal animal) {
        if (System.currentTimeMillis() - animal.getNextMaintenance() >= AnimalUtil.getAnimalMaintenance.get("sheep")) {
            farm.setTotalWool(farm.getTotalWool() + 1);
            animal.setNextMaintenance(System.currentTimeMillis());
        }
    }
}
