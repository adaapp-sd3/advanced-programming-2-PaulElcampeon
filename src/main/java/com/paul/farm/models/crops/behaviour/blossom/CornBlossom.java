package com.paul.farm.models.crops.behaviour.blossom;

import com.paul.farm.models.Farm;
import com.paul.farm.models.crops.Crop;
import com.paul.farm.models.fields.GrowingField;
import lombok.Data;

@Data
public class CornBlossom implements  Blossom {

    @Override
    public void blossom(Farm farm, Crop crop) {
        crop.setBlossomed(true);
        farm.setCornSeed(farm.getCornSeed() + 5);
        farm.setTotalCorn(farm.getTotalCorn() + 10.0);//when a corn plant blossoms we add 10 corn plants to our stock
    }
}
