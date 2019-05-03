package com.paul.farm.models.crops;

import com.paul.farm.enums.CropType;
import com.paul.farm.models.crops.behaviour.blossom.GrassBlossom;
import com.paul.farm.utils.CropUtils;
import lombok.Data;

@Data
public class Grass extends Crop {

    public Grass() {
        super(new GrassBlossom());
        setCropType(CropType.GRASS);
        setHarvestAge(CropUtils.getHarvestableAge().get("grass"));
    }

    public Grass(int x, int y) {
        super(x, y, new GrassBlossom());
        setCropType(CropType.GRASS);
        setHarvestAge(CropUtils.getHarvestableAge().get("grass"));
    }
}
