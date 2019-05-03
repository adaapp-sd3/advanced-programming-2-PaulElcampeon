package com.paul.farm.models.fields;

import com.paul.farm.enums.FieldType;
import com.paul.farm.models.animals.Animal;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        Animal animal = (Animal) object;
        animal.setXPos(ThreadLocalRandom.current().nextInt(this.getXPos() + 1, this.getXPos() + this.getWidth() - 50));
        animal.setYPos(ThreadLocalRandom.current().nextInt(this.getYPos() + 1, this.getYPos() + this.getHeight() - 50));
    }
}
