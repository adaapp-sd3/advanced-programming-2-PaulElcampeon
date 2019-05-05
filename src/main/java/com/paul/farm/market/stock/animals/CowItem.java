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
        for (int i = 0; i < farm.getFields().size(); i++) {
            if (farm.getFields().get(i) instanceof GrazingField && (farm.getFields().get(i).getNoOfAnimals() < 20 && farm.getTotalCows() < 100) ||  farm.getFields().get(i) instanceof PettingFarmField && (farm.getFields().get(i).getNoOfAnimals() < 20 && farm.getTotalCows() < 100)) {
                farm.setTotalCows(farm.getTotalCows() + 1);
                farm.setBudget(farm.getBudget() - getBuyPrice());
                Cow cow = new Cow();
                cow.setFieldIndex(i);
                farm.getFields().get(i).setNoOfAnimals(farm.getFields().get(i).getNoOfAnimals() + 1);
                farm.getFields().get(i).addToField(cow);
                farm.getAnimals().add(cow);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalCows() > 0) {
            farm.setTotalCows(farm.getTotalCows() - 1);
            farm.setBudget(farm.getBudget() + getSellPrice());
            Cow cow = null;
            for (int i = 0; i < farm.getAnimals().size(); i++) {
                if (farm.getAnimals().get(i) instanceof Cow) {
                    cow = (Cow) farm.getAnimals().get(i);
                    farm.getFields().get(cow.getFieldIndex()).setNoOfAnimals(farm.getFields().get(cow.getFieldIndex()).getNoOfAnimals() - 1);
                    break;
                }
            }

            farm.getAnimals().remove(cow);
            return true;
        }
        return false;
    }

    public static CowItem getInstance() {
        return new CowItem();
    }
}
