package com.paul.farm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlantSeedRequestDto {

    private String farmName;
    private int fieldIndex;
    private String seed;
//    private int x;
//    private int y;

    public PlantSeedRequestDto() {}
}
