package com.paul.farm.models.fields;

import com.paul.farm.enums.FieldType;
import com.paul.farm.models.position.Point;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
public abstract class Field {

    private FieldType fieldType;
    private int noOfAnimals;
    private int xPos;
    private int yPos;
    private int width = 300;
    private int height = 200;

    public abstract void addToField(Object object);

    public Field() {
    }

    public Field(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public static List<Field> getStartingFields() {
        return new ArrayList<Field>() {
            {
                add(new GrowingField(getFieldPositions.get(0).getX(), getFieldPositions.get(0).getY()));
                add(new GrowingField(getFieldPositions.get(1).getX(), getFieldPositions.get(1).getY()));
            }
        };
    }

    public static HashMap<Integer, Point> getFieldPositions = new HashMap<Integer, Point>() {{
        put(0, new Point(32, 20));
        put(1, new Point(32, 270));
        put(2, new Point(32, 515));
        put(3, new Point(366, 20));
        put(4, new Point(366, 270));
    }};
}
