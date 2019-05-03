package com.paul.farm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FarmModificationRequestDto {

    private String farmName;
    private int fieldIndex;
}
