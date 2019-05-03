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
        for (int i = 0; i < farm.getFields().size(); i++) {
            if (farm.getFields().get(i) instanceof GrazingField && farm.getFields().get(i).getNoOfAnimals() < 20) {
                farm.setTotalChickens(farm.getTotalChickens() + 1);
                farm.setBudget(farm.getBudget() - getBuyPrice());
                Chicken chicken = new Chicken();
                chicken.setFieldIndex(i);
                farm.getFields().get(i).setNoOfAnimals(farm.getFields().get(i).getNoOfAnimals() + 1);
                farm.getFields().get(i).addToField(chicken);
                farm.getAnimals().add(chicken);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalChickens() > 0) {
            farm.setTotalChickens(farm.getTotalChickens() - 1);
            farm.setBudget(farm.getBudget() + getSellPrice());
            Chicken chicken = null;
            for (int i = 0; i < farm.getAnimals().size(); i++) {
                if (farm.getAnimals().get(i) instanceof Chicken) {
                    chicken = (Chicken) farm.getAnimals().get(i);
                    farm.getFields().get(chicken.getFieldIndex()).setNoOfAnimals(farm.getFields().get(chicken.getFieldIndex()).getNoOfAnimals() - 1);
                    break;
                }
            }

            farm.getAnimals().remove(chicken);
            return true;
        }
        return false;
    }

    public static ChickenItem getInstance() {
        return new ChickenItem();
    }
}
