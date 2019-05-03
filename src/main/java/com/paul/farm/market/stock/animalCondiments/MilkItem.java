package com.paul.farm.market.stock.animalCondiments;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class MilkItem extends MarketItem {

    public MilkItem() {
        super(0, 8);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalMilk() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalMilk(farm.getTotalMilk() - 1);
            return true;
        }
        return false;
    }

    public static MilkItem getInstance() {
        return new MilkItem();
    }
}
