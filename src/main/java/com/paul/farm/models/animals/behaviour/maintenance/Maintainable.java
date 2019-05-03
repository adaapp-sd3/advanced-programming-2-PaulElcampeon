package com.paul.farm.models.animals.behaviour.maintenance;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;

public interface Maintainable {

    void maintain(Farm farm, Animal animal);
}
