package com.paul.farm.models.crops;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paul.farm.enums.CropType;
import com.paul.farm.models.crops.behaviour.blossom.Blossom;
import com.paul.farm.models.crops.behaviour.harvestable.Harvestable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Crop {

    private CropType cropType;
    private int xPos;
    private int yPos;
    private long age;
    private long harvestAge;
    @JsonIgnore
    private int indexOfField;
    @JsonIgnore
    private Harvestable harvestable = new Harvestable();
    @JsonIgnore
    private Blossom blossom;
    private boolean blossomed;


    public Crop() {
        this.age = System.currentTimeMillis();
    }

    public Crop(int x, int y, Blossom blossom) {
        this.xPos = x;
        this.yPos = y;
        this.blossom = blossom;
        this.age = System.currentTimeMillis();
    }

    public Crop(Blossom blossom) {
        this.age = System.currentTimeMillis();
        this.blossom = blossom;
    }
}
