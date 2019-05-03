package com.paul.farm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarketRequest {

    private String farmName;
    private String item;
    private int units;

    public MarketRequest() {}
}
