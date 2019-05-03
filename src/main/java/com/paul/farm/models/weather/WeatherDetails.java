package com.paul.farm.models.weather;

import lombok.Data;

@Data
class WeatherDetails {

    private double temp;
    private int pressure;
    private int humidity;
    private double temp_min;
    private double temp_max;


}
