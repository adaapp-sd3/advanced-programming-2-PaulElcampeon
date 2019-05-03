package com.paul.farm.market.stock.field;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrowingField;
import com.paul.farm.models.position.Point;
import lombok.Data;

@Data
public class FieldItem extends MarketItem {

    public FieldItem() {
        super(400,0);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        if (farm.getFields().size() != 5) {
            Point point = Field.getFieldPositions.get(farm.getFields().size());
            farm.getFields().add(new GrowingField(point.getX(), point.getY()));
            farm.setBudget(farm.getBudget() - getBuyPrice());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        return false;
    }

    public static FieldItem getInstance() {
        return new FieldItem();
    }
}
