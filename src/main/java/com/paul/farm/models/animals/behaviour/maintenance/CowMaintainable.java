package com.paul.farm.models.animals.behaviour.maintenance;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.models.fields.PettingFarmField;
import com.paul.farm.utils.AnimalUtil;

public class CowMaintainable implements Maintainable {
    @Override
    public void maintain(Farm farm, Animal animal) {
        if (System.currentTimeMillis() - animal.getNextMaintenance() >= AnimalUtil.getAnimalMaintenance.get("cow")) {
            farm.setTotalMilk(farm.getTotalMilk() + 1);
            animal.setNextMaintenance(System.currentTimeMillis());//if 5 mins has passed since this time then we milk it
        }
    }
}
