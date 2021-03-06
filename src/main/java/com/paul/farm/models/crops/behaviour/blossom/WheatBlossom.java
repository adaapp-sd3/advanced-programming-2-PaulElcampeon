package com.paul.farm.models.crops.behaviour.blossom;

import com.paul.farm.models.Farm;
import com.paul.farm.models.crops.Crop;
import com.paul.farm.models.fields.GrowingField;
import lombok.Data;

@Data
public class WheatBlossom implements Blossom {

    @Override
    public void blossom(Farm farm , Crop crop) {
        crop.setBlossomed(true);
        farm.setWheatSeed(farm.getWheatSeed() + 5);//when blossoms it produces more wheat seed
        farm.setTotalBread(farm.getTotalBread() + 10);
    }
}
