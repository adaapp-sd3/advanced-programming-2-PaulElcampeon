package com.paul.farm.models.animals;

import com.paul.farm.enums.Breed;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.behaviour.graze.CattleGrazable;
import com.paul.farm.models.animals.behaviour.maintenance.CowMaintainable;
import com.paul.farm.models.animals.behaviour.maturity.CowMaturable;
import lombok.Data;

@Data
public class Cow extends Animal {

    @Override
    public void reduceNumber(Farm farm) {
        farm.getFields().get(getFieldIndex()).setNoOfAnimals(farm.getFields().get(getFieldIndex()).getNoOfAnimals() - 1);
        farm.setTotalCows(farm.getTotalCows() - 1);
    }

    public Cow() {
        super(new CattleGrazable());
        setBreed(Breed.COW);
        setMaintainable(new CowMaintainable());
        setMaturable(new CowMaturable());
    }

    public Cow(int xPos, int yPos) {
        super(xPos, yPos, new CattleGrazable());
        setBreed(Breed.COW);
        setMaintainable(new CowMaintainable());
        setMaturable(new CowMaturable());
    }
}
