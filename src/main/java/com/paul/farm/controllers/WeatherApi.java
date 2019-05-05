package com.paul.farm.controllers;

import com.paul.farm.dtos.WeatherResDto;
import com.paul.farm.models.weather.WeatherResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class WeatherApi {

    private Logger logger = Logger.getLogger(WeatherApi.class.getName());
    private RestTemplate restTemplate = new RestTemplate();
    private static String weather = "clear";
    //    private final String APIKEY = "f495a402791890d354356732fc9d93d7";
    private final String APICALL = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=f495a402791890d354356732fc9d93d7";

    public static String getCurrentWeather() {
        return weather;
    }

    public void setWeather(String newWeather) {
        this.weather = newWeather;
    }


    @RequestMapping(value = "/get/weather", method = RequestMethod.GET)
    public WeatherResDto serveWeather() {
        return new WeatherResDto(getCurrentWeather());
    }


    @Scheduled(fixedDelay = 600000)//calling every 10 mins
    public void getWeather() {
        WeatherResponse weatherResponse = restTemplate.getForObject(APICALL, WeatherResponse.class);
        String retrievedWeather = weatherResponse.getWeather().get(0).getMain().toLowerCase();
        if (retrievedWeather.equals("clear") || retrievedWeather.equals("haze")) {
            setWeather("clear");
        } else if (retrievedWeather.equals("rain") || retrievedWeather.equals("drizzle")) {
            setWeather("rain");
        } else if (retrievedWeather.equals("snow")) {
            setWeather(retrievedWeather);
        } else if (retrievedWeather.equals("clouds")) {//no animation for clouds so we will just use clear
//            setWeather(retrievedWeather);
            setWeather("clear");
        } else {
            setWeather("clear");
        }
        logger.log(Level.INFO, String.format("A request was made to the weather API the current weather is set to %s", getCurrentWeather().toUpperCase()));
    }
}
