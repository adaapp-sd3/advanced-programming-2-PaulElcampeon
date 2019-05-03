package com.paul.farm.models.animals.behaviour.maturity;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;

public interface Maturable {

    void mature(Farm farm, Animal animal);
}
