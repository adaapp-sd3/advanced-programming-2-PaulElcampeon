package com.paul.farm.models.animals.behaviour.graze;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import lombok.Data;

public interface Grazable {

//    private Farm farm;
//    private animal animal;
//
//    public Grazable() {}
//
//    public Grazable(Farm farm, animal animal) {
//        this.farm = farm;
//        this.animal = animal;
//    }

    public abstract void graze(Farm farm, Animal animal);
}
