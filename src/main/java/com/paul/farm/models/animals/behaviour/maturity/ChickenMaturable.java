package com.paul.farm.models.animals.behaviour.maturity;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;

public class ChickenMaturable implements Maturable {
    @Override
    public void mature(Farm farm, Animal animal) {
        //what do we do with a chicken that has reached maturing?
        //do nothing
    }
}
