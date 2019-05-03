package com.paul.farm.models.weather;

import lombok.Data;

@Data
class WeatherSys {
    private int type;
    private int id;
    private double message;
    private String country;
    private long sunrise;
    private long sunset;



}
