package com.paul.farm.market.stock.animalCondiments;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class LambItem extends MarketItem {

    public LambItem() {
        super(0, 15);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalLamb() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalLamb(farm.getTotalLamb() - 1);
            return true;
        }
        return false;
    }

    public static LambItem getInstance() {
        return new LambItem();
    }
}
