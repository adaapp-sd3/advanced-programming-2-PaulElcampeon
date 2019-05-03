package com.paul.farm.models.animals;

import com.paul.farm.enums.Breed;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.behaviour.graze.CattleGrazable;
import com.paul.farm.models.animals.behaviour.maintenance.SheepMaintainable;
import com.paul.farm.models.animals.behaviour.maturity.SheepMaturable;
import lombok.Data;

@Data
public class Sheep extends Animal {

    @Override
    public void reduceNumber(Farm farm) {
        farm.getFields().get(getFieldIndex()).setNoOfAnimals(farm.getFields().get(getFieldIndex()).getNoOfAnimals() - 1);
        farm.setTotalSheep(farm.getTotalSheep() - 1);
    }

    public Sheep() {
        super(new CattleGrazable());
        setBreed(Breed.SHEEP);
        setMaintainable(new SheepMaintainable());
        setMaturable(new SheepMaturable());
    }

    public Sheep(int xPos, int yPos) {
        super(xPos, yPos, new CattleGrazable());
        setBreed(Breed.SHEEP);
        setMaintainable(new SheepMaintainable());
        setMaturable(new SheepMaturable());
    }
}
