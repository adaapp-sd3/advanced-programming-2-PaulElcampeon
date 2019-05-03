package com.paul.farm.market.stock.animalCondiments;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class BeefItem extends MarketItem {

    public BeefItem() {
        super(0, 10);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalBeef() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalBeef(farm.getTotalBeef() - 1);
            return true;
        }
        return false;
    }

    public static BeefItem getInstance() {
        return new BeefItem();
    }
}
