package com.paul.farm.models.animals;

import com.paul.farm.enums.Breed;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.behaviour.graze.ChickenGrazable;
import com.paul.farm.models.animals.behaviour.maintenance.ChickenMaintainable;
import com.paul.farm.models.animals.behaviour.maturity.ChickenMaturable;
import lombok.Data;

@Data
public class Chicken extends Animal {

    @Override
    public void reduceNumber(Farm farm) {
        farm.getFields().get(getFieldIndex()).setNoOfAnimals(farm.getFields().get(getFieldIndex()).getNoOfAnimals() - 1);
        farm.setTotalChickens(farm.getTotalChickens() - 1);
    }

    public Chicken() {
        super(new ChickenGrazable());
        setBreed(Breed.CHICKEN);
        setMaintainable(new ChickenMaintainable());
        setMaturable(new ChickenMaturable());
    }

    public Chicken(int x, int y) {
        super(x, y, new ChickenGrazable());
        setBreed(Breed.CHICKEN);
        setMaintainable(new ChickenMaintainable());
        setMaturable(new ChickenMaturable());
    }
}
