package com.paul.farm.tourism;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.fields.Field;
import com.paul.farm.utils.AnimalUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Data
public class Tourist extends Animal {

    private static Logger logger = Logger.getLogger(Tourist.class.getName());

    private String farmName = "";
    private int money = 50;
    private int xPos;
    private int yPos;
    private long timeArrived = 0;
    private long timeSpentVisiting = 60000;//1 minute;
    @JsonIgnore
    private Field field;
    @JsonIgnore
    private LeaveFarm leaveFarm = new LeaveFarm();

    private static List<Tourist> tourists = Arrays.asList(new Tourist(), new Tourist(), new Tourist(), new Tourist(), new Tourist(), new Tourist(), new Tourist(), new Tourist(), new Tourist(), new Tourist());

    public static List<Tourist> getUnavailableTourists() {
        return tourists.stream().filter(tourist -> tourist.getFarmName().length() > 0).collect(Collectors.toList());
    }

    public static List<Tourist> getAvailableTouristList() {
        ArrayList<Tourist> tempTourists = new ArrayList<>();
        for (Tourist tourist : tourists) {
            if (tourist.getFarmName().equals("")) {
                tempTourists.add(tourist);
            }
        }
        return tempTourists;
    }

    public static void process(HashMap<String, Integer> farmAndNoOfVisitors) {
        tourists = tourists.stream().map(tourist -> {
            boolean result = tourist.getLeaveFarm().leaveIfHaveTo(tourist);
            if (result) {//if tourist has spent too much time on farm then we remove them from farm;
                logger.log(Level.INFO, String.format("A tourist has finished their visit to farm %s", tourist.getFarmName()));
                if (farmAndNoOfVisitors.containsKey(tourist.getFarmName())) {//due to concurrency issues, sometimes we remove the key value pair this is most probably due to terminating the application and starting it again but animals have been removed from petting farm field whilst we still have tourists present thus the wallet is not created, so when we call the wallet it throws a null point exception
                    farmAndNoOfVisitors.put(tourist.getFarmName(), farmAndNoOfVisitors.get(tourist.getFarmName()) - 1);
                    logger.log(Level.INFO, String.format("No of visitors in farm %s is %d", tourist.getFarmName(), farmAndNoOfVisitors.get(tourist.getFarmName())));
                }
                    tourist.setFarmName("");
                    tourist.setField(null);
            } else {
                if (tourist.getField() != null) {
                    AnimalUtil.moveAnimal(tourist, tourist.getField());
                }
            }
            return tourist;
        }).collect(Collectors.toList());
    }

    public static void move(Field field) {

    }

    @Override
    public void reduceNumber(Farm farm) {

    }
}
