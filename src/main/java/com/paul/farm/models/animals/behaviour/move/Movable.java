package com.paul.farm.models.animals.behaviour.move;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.fields.Field;
import com.paul.farm.utils.AnimalUtil;
import lombok.Data;

@Data
public class Movable {

    public void move(Farm farm, Animal animal) {
        AnimalUtil.moveAnimal(animal, farm.getFields().get(animal.getFieldIndex()));
    }
}
