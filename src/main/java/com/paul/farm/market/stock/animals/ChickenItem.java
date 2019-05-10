package com.paul.farm.market.stock.animals;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Chicken;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.models.fields.PettingFarmField;
import lombok.Data;

@Data
public class ChickenItem extends MarketItem {

    public ChickenItem() {
        super(40, 30);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        int fieldCounter = 0;
        for (Field field : farm.getFields()) {
            if (field instanceof GrazingField && (field.getNoOfAnimals() < 20 && farm.getTotalChickens() < 100) || field instanceof PettingFarmField && (field.getNoOfAnimals() < 20 && farm.getTotalChickens() < 100)) {
                farm.setTotalChickens(farm.getTotalChickens() + 1);
                farm.setBudget(farm.getBudget() - getBuyPrice());
                Chicken chicken = new Chicken();
                chicken.setFieldIndex(fieldCounter);
                field.setNoOfAnimals(field.getNoOfAnimals() + 1);
                field.addToField(chicken);
                farm.getAnimals().add(chicken);
                return true;
            }
            fieldCounter++;
        }
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalChickens() > 0) {
            farm.setTotalChickens(farm.getTotalChickens() - 1);
            farm.setBudget(farm.getBudget() + getSellPrice());
            return farm.getAnimals()
                    .stream()
                    .filter(animal -> animal instanceof Chicken)
                    .findFirst()
                    .map(animal -> {
                        farm.getAnimals().remove(animal);
                        farm.getFields().get(animal.getFieldIndex()).setNoOfAnimals(farm.getFields().get(animal.getFieldIndex()).getNoOfAnimals() - 1);
                        return true;
                    })
                    .orElse(false);
        }
        return false;
    }

    public static ChickenItem getInstance() {
        return new ChickenItem();
    }
}
