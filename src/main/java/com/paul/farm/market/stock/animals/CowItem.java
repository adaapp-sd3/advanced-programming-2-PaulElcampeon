package com.paul.farm.market.stock.animals;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Cow;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.models.fields.PettingFarmField;
import lombok.Data;

@Data
public class CowItem extends MarketItem {

    public CowItem() {
        super(150, 120);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        int fieldCounter = 0;
        for (Field field : farm.getFields()) {
            if (field instanceof GrazingField && (field.getNoOfAnimals() < 20 && farm.getTotalCows() < 100) || field instanceof PettingFarmField && (field.getNoOfAnimals() < 20 && farm.getTotalCows() < 100)) {
                farm.setTotalCows(farm.getTotalCows() + 1);
                farm.setBudget(farm.getBudget() - getBuyPrice());
                Cow cow = new Cow();
                cow.setFieldIndex(fieldCounter);
                field.setNoOfAnimals(field.getNoOfAnimals() + 1);
                field.addToField(cow);
                farm.getAnimals().add(cow);
                return true;
            }
            fieldCounter++;
        }
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalCows() > 0) {
            farm.setTotalCows(farm.getTotalCows() - 1);
            farm.setBudget(farm.getBudget() + getSellPrice());
            return farm.getAnimals()
                    .stream()
                    .filter(animal -> animal instanceof Cow)
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

    public static CowItem getInstance() {
        return new CowItem();
    }
}
