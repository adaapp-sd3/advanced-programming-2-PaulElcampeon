package com.paul.farm.models.weather;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

    private WeatherPoints coord;
    private List<Weather> weather;
    private String base;
    private WeatherDetails main;
    private int visibility;
    private WindDetails wind;
    private CloudDetails clouds;
    private long dt;
    private WeatherSys sys;
    private long id;
    private String name;
    private int cod;
}
