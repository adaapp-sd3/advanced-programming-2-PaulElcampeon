package com.paul.farm.models.fields;

import com.paul.farm.enums.FieldType;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.tourism.Tourist;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Data
public class PettingFarmField extends Field {

    public PettingFarmField() {
        super();
        setFieldType(FieldType.PETTINGFARM);
    }

    public PettingFarmField(int x, int y) {
        super(x, y);
        setFieldType(FieldType.PETTINGFARM);
    }

    @Override
    public void addToField(Object object) {
        if (object instanceof Animal) {
            Animal animal = ((Animal) object);
            animal.setXPos(ThreadLocalRandom.current().nextInt(this.getXPos() + 10, this.getXPos() + this.getWidth() - 50));
            animal.setYPos(ThreadLocalRandom.current().nextInt(this.getYPos() + 10, this.getYPos() + this.getHeight() - 50));
        } else {
            Tourist tourist = ((Tourist) object);
            tourist.setXPos(ThreadLocalRandom.current().nextInt(this.getXPos() + 1, this.getXPos() + this.getWidth() - 50));
            tourist.setYPos(ThreadLocalRandom.current().nextInt(this.getYPos() + 1, this.getYPos() + this.getHeight() - 50));
        }
    }

    @Override
    public void clearField(Farm farm, int indexOfField) {
        this.setNoOfAnimals(0);
        farm.setAnimals(farm.getAnimals().stream().filter(animal -> animal.getFieldIndex() != indexOfField).collect(Collectors.toList()));
        farm.updateAnimalCount();
    }
}
