package com.paul.farm.market.stock.animals;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Chicken;
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
        for (int i = 0; i < farm.getFields().size(); i++) {
            if (farm.getFields().get(i) instanceof GrazingField && farm.getFields().get(i).getNoOfAnimals() < 20) {
                farm.setTotalSheep(farm.getTotalSheep() + 1);
                farm.setBudget(farm.getBudget() - getBuyPrice());
                Sheep sheep = new Sheep();
                sheep.setFieldIndex(i);
                farm.getFields().get(i).setNoOfAnimals(farm.getFields().get(i).getNoOfAnimals() + 1);
                farm.getFields().get(i).addToField(sheep);
                farm.getAnimals().add(sheep);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalSheep() > 0) {
            farm.setTotalSheep(farm.getTotalSheep() - 1);
            farm.setBudget(farm.getBudget() + getSellPrice());
            Sheep sheep = null;
            for (int i = 0; i < farm.getAnimals().size(); i++) {
                if (farm.getAnimals().get(i) instanceof Sheep) {
                    sheep = (Sheep) farm.getAnimals().get(i);
                    farm.getFields().get(sheep.getFieldIndex()).setNoOfAnimals(farm.getFields().get(sheep.getFieldIndex()).getNoOfAnimals() - 1);
                    break;
                }
            }
            farm.getAnimals().remove(sheep);
            return true;
        }
        return false;
    }

    public static SheepItem getInstance() {
        return new SheepItem();
    }
}
