package com.paul.farm.models.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paul.farm.enums.Breed;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.behaviour.dying.Deathable;
import com.paul.farm.models.animals.behaviour.graze.Grazable;
import com.paul.farm.models.animals.behaviour.maintenance.Maintainable;
import com.paul.farm.models.animals.behaviour.maturity.Maturable;
import com.paul.farm.models.animals.behaviour.move.Movable;
import lombok.Data;

@Data
public abstract class Animal {

    private Breed breed;
    private int xPos;
    private int yPos;
    private double hungerLevel = 100;
    private long birthDate = System.currentTimeMillis();
    private boolean dead;
    private long nextMaintenance;
    @JsonIgnore
    private Maintainable maintainable;
    @JsonIgnore
    private Maturable maturable;
    @JsonIgnore
    private int fieldIndex;
    @JsonIgnore
    private Deathable deathable = new Deathable();
    @JsonIgnore
    private Grazable grazable;
    @JsonIgnore
    private Movable movable = new Movable();

    public abstract void reduceNumber(Farm farm);


    public Animal() {
    }

    public Animal(Grazable grazable) {
        this.grazable = grazable;
    }

    public Animal(int xPos, int yPos, Grazable grazable) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.grazable = grazable;
    }
}
