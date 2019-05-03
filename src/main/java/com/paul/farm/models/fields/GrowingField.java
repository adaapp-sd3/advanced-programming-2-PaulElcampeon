package com.paul.farm.models.fields;

import com.paul.farm.enums.FieldType;
import com.paul.farm.models.crops.Crop;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class GrowingField extends Field {

    public GrowingField() {
        super();
        setFieldType(FieldType.GROWING);
    }

    public GrowingField(int x, int y) {
        super(x, y);
        setFieldType(FieldType.GROWING);
    }

    @Override
    public void addToField(Object object) {
        Crop crop = (Crop) object;
        crop.setXPos(ThreadLocalRandom.current().nextInt(this.getXPos() + 1, this.getXPos() + this.getWidth() - 20));
        crop.setYPos(ThreadLocalRandom.current().nextInt(this.getYPos() + 1, this.getYPos() + this.getHeight() - 20));
    }
}
