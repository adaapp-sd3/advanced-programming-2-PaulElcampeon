package com.paul.farm.models.crops.behaviour.harvestable;

import com.paul.farm.models.Farm;
import com.paul.farm.models.crops.Crop;
import lombok.Data;

@Data
public class Harvestable {

    public void harvest(Farm farm, Crop crop) {
        if (System.currentTimeMillis() - crop.getAge() >= crop.getHarvestAge()) {
            crop.getBlossom().blossom(farm, crop);
        }
    }
}
