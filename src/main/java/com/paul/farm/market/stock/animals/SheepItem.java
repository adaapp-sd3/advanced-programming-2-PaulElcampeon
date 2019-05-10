package com.paul.farm.market.stock.animals;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Sheep;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.models.fields.PettingFarmField;
import lombok.Data;

@Data
public class SheepItem extends MarketItem {

    public SheepItem() {
        super(100, 80);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        int fieldCounter = 0;
        for (Field field : farm.getFields()) {
            if (field instanceof GrazingField && (field.getNoOfAnimals() < 20 && farm.getTotalSheep() < 100) || field instanceof PettingFarmField && (field.getNoOfAnimals() < 20 && farm.getTotalSheep() < 100)) {
                farm.setTotalSheep(farm.getTotalSheep() + 1);
                farm.setBudget(farm.getBudget() - getBuyPrice());
                Sheep sheep = new Sheep();
                sheep.setFieldIndex(fieldCounter);
                field.setNoOfAnimals(field.getNoOfAnimals() + 1);
                field.addToField(sheep);
                farm.getAnimals().add(sheep);
                return true;
            }
            fieldCounter++;
        }
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalSheep() > 0) {
            farm.setTotalSheep(farm.getTotalSheep() - 1);
            farm.setBudget(farm.getBudget() + getSellPrice());
            return farm.getAnimals()
                    .stream()
                    .filter(animal -> animal instanceof Sheep)
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

    public static SheepItem getInstance() {
        return new SheepItem();
    }
}
