package com.paul.farm.tourism;

import com.paul.farm.models.Farm;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TourismRevenue {

    private ConcurrentHashMap<String, Integer> revenueFromPettingFarm = new ConcurrentHashMap<>();

    private Logger logger = Logger.getLogger(TourismRevenue.class.getName());

    public void addFarmWallet(Farm farm) {
        revenueFromPettingFarm.putIfAbsent(farm.getFarmName(), 0);
    }

    public void addMoneyToFarmWallet(Tourist tourist) {
        logger.log(Level.INFO, String.format("Money was added to farm %s wallet from a tourist", tourist.getFarmName()));
        revenueFromPettingFarm.put(tourist.getFarmName(), revenueFromPettingFarm.get(tourist.getFarmName()) + tourist.getMoney());
    }

    public void collectMoneyFromWallet(Farm farm) {
        farm.setBudget(farm.getBudget() + revenueFromPettingFarm.put(farm.getFarmName(), 0));
    }

    public void removeFarmWalletIfPresent(Farm farm) {
        revenueFromPettingFarm.remove(farm.getFarmName());
    }
}
