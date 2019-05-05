package com.paul.farm.utils;

import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.fields.Field;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalUtil {

    public static void moveAnimal(Animal animal, Field field) {
        int randomNumber = ThreadLocalRandom.current().nextInt(4);
        if (randomNumber == 0) {
            moveLeft(animal, field);
        }

        if (randomNumber == 1) {
            moveRight(animal, field);
        }

        if (randomNumber == 2) {
            moveDown(animal, field);
        }

        if (randomNumber == 3) {
            moveUp(animal, field);
        }
    }

    public static void moveLeft(Animal animal, Field field) {
        if (animal.getXPos() - 5 <= field.getXPos()) {
            animal.setXPos(animal.getXPos() - 2);
        } else {
            animal.setXPos(animal.getXPos() - 3);
        }
    }

    public static void moveRight(Animal animal, Field field) {
        if (animal.getXPos() + 40 >= field.getXPos() + field.getWidth()) {
            animal.setXPos(animal.getXPos() - 2);
        } else {
            animal.setXPos(animal.getXPos() + 3);
        }
    }

    public static void moveUp(Animal animal, Field field) {
        if (animal.getYPos() - 5 <= field.getYPos()) {
            animal.setYPos(animal.getYPos() - 2);
        } else {
            animal.setYPos(animal.getYPos() - 3);
        }
    }

    public static void moveDown(Animal animal, Field field) {
        if (animal.getYPos() + 40 >= field.getYPos() + field.getHeight()) {
            animal.setYPos(animal.getYPos() - 2);
        } else {
            animal.setYPos(animal.getYPos() + 3);
        }
    }

    public static HashMap<String, Long> getAnimalMaintenance = new HashMap<String, Long>(){{
        put("cow", 120000l);//2 mins
        put("sheep", 180000l);//3 mins
        put("chicken", 60000l);//1 mins
    }};

    public static HashMap<String, Long> getAnimalFullMature = new HashMap<String, Long>(){{
        put("cow", 600000l);//10 mins
        put("sheep", 540000l);//9 mins
        put("chicken", 420000l);//7mins
    }};
}
