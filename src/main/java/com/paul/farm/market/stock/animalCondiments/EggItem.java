package com.paul.farm.market.stock.animalCondiments;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class EggItem extends MarketItem {

    public EggItem() {
        super(0, 5);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalEggs() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalEggs(farm.getTotalEggs() - 1);
            return true;
        }
        return false;
    }

    public static EggItem getInstance() {
        return new EggItem();
    }
}
