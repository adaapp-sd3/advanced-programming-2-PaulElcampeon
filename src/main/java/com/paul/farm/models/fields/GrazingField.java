package com.paul.farm.models.fields;

import com.paul.farm.enums.FieldType;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Data
public class GrazingField extends Field {

    public GrazingField() {
        super();
        setFieldType(FieldType.GRAZZING);
    }

    public GrazingField(int x, int y) {
        super(x, y);
        setFieldType(FieldType.GRAZZING);
    }

    @Override
    public void addToField(Object object) {
        Animal animal = (Animal) object;
        animal.setXPos(ThreadLocalRandom.current().nextInt(this.getXPos() + 10, this.getXPos() + this.getWidth() - 50));
        animal.setYPos(ThreadLocalRandom.current().nextInt(this.getYPos() + 10, this.getYPos() + this.getHeight() - 50));
    }

    @Override
    public void clearField(Farm farm, int indexOfField) {
        this.setNoOfAnimals(0);
        farm.setAnimals(farm.getAnimals().stream().filter(animal -> animal.getFieldIndex() != indexOfField).collect(Collectors.toList()));
        farm.updateAnimalCount();
    }
}
