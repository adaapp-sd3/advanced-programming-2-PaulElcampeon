package com.paul.farm.dtos;

import lombok.Data;

@Data
public class MoveAnimalDto {

    private String farmName;
    private String animal;
    private String fieldType;
    private int fieldIndex;
}
