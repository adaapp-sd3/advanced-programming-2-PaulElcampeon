package com.paul.farm.models.animals.behaviour.growth;

import com.paul.farm.models.animals.Animal;
import lombok.Data;

@Data
public class Growable {

    public void grow(Animal animal) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - animal.getBirthDate() > 120000) {
            animal.setGrowthLevel(2);
        }
        if (currentTime - animal.getBirthDate() > 240000) {
            animal.setGrowthLevel(3);
        }
        if (currentTime - animal.getBirthDate() > 360000) {
            animal.setGrowthLevel(4);
        }
    }

}
