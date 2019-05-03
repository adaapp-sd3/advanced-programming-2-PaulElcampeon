package com.paul.farm.models.crops;

import com.paul.farm.enums.CropType;
import com.paul.farm.models.crops.behaviour.blossom.CornBlossom;
import com.paul.farm.utils.CropUtils;
import lombok.Data;

@Data
public class Corn extends Crop {

    public Corn() {
        super(new CornBlossom());
        setCropType(CropType.CORN);
        setHarvestAge(CropUtils.getHarvestableAge().get("corn"));
    }

    public Corn(int x, int y) {
        super(x, y, new CornBlossom());
        setCropType(CropType.CORN);
        setHarvestAge(CropUtils.getHarvestableAge().get("corn"));
    }
}

