package com.paul.farm.utils;


import java.util.HashMap;

public class CropUtils {

    private static HashMap<String, Long> cropHarvestableAge = new HashMap<>();

    static {
        cropHarvestableAge.put("corn", 240000L);//4mins
        cropHarvestableAge.put("grass", 300000L);//5 mins
        cropHarvestableAge.put("wheat", 400000L);//8mins
    }

    public static HashMap<String, Long> getHarvestableAge() {
        return cropHarvestableAge;
    }
}
