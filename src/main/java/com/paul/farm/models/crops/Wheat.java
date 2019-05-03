package com.paul.farm.models.crops;

import com.paul.farm.enums.CropType;
import com.paul.farm.models.crops.behaviour.blossom.WheatBlossom;
import com.paul.farm.utils.CropUtils;
import lombok.Data;

@Data
public class Wheat extends Crop {

    public Wheat() {
        super(new WheatBlossom());
        setCropType(CropType.WHEAT);
        setHarvestAge(CropUtils.getHarvestableAge().get("wheat"));
    }

    public Wheat(int x, int y) {
        super(x, y, new WheatBlossom());
        setCropType(CropType.WHEAT);
        setHarvestAge(CropUtils.getHarvestableAge().get("wheat"));
    }
}
