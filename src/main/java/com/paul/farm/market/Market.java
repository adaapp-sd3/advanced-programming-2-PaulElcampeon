package com.paul.farm.market;

import com.paul.farm.dtos.MarketRequest;
import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.market.stock.animalCondiments.*;
import com.paul.farm.market.stock.animalFeed.StrawItem;
import com.paul.farm.market.stock.animals.ChickenItem;
import com.paul.farm.market.stock.animals.CowItem;
import com.paul.farm.market.stock.animals.SheepItem;
import com.paul.farm.market.stock.crop.CornItem;
import com.paul.farm.market.stock.crop.WheatItem;
import com.paul.farm.market.stock.field.FieldItem;
import com.paul.farm.market.stock.produce.BreadItem;
import com.paul.farm.market.stock.seed.CornSeedItem;
import com.paul.farm.market.stock.seed.GrassSeedItem;
import com.paul.farm.market.stock.seed.WheatSeedItem;
import com.paul.farm.models.Farm;
import com.paul.farm.services.interfaces.FarmService;
import com.paul.farm.utils.MessageUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@Component
public class Market {

    @Autowired
    private FarmService farmService;

    @Autowired
    private MessageUtil messageUtil;

    private Logger logger = Logger.getLogger(Market.class.getName());

    private static HashMap<String, MarketItem> itemsToBuy = new HashMap<>();
    private static HashMap<String, MarketItem> itemsToSell = new HashMap<>();
    private static AtomicInteger money = new AtomicInteger(20000);
    private static AtomicInteger grassSeed = new AtomicInteger(1000);
    private static AtomicInteger wheatSeed = new AtomicInteger(1000);
    private static AtomicInteger cornSeed = new AtomicInteger(1000);

    static {
        itemsToBuy.put("cow", CowItem.getInstance());
        itemsToBuy.put("sheep", SheepItem.getInstance());
        itemsToBuy.put("chicken", ChickenItem.getInstance());
        itemsToBuy.put("straw", StrawItem.getInstance());
        itemsToBuy.put("corn", CornItem.getInstance());
        itemsToBuy.put("grass seed", GrassSeedItem.getInstance());
        itemsToBuy.put("wheat seed", WheatSeedItem.getInstance());
        itemsToBuy.put("corn seed", CornSeedItem.getInstance());
        itemsToBuy.put("field", FieldItem.getInstance());

        itemsToSell.put("cow", CowItem.getInstance());
        itemsToSell.put("sheep", SheepItem.getInstance());
        itemsToSell.put("chicken", ChickenItem.getInstance());
        itemsToSell.put("wheat", WheatItem.getInstance());
        itemsToSell.put("corn", CornItem.getInstance());
        itemsToSell.put("beef", BeefItem.getInstance());
        itemsToSell.put("wool", WoolItem.getInstance());
        itemsToSell.put("bread", BreadItem.getInstance());
        itemsToSell.put("egg", EggItem.getInstance());
        itemsToSell.put("lamb", LambItem.getInstance());
        itemsToSell.put("milk", MilkItem.getInstance());
    }

    public Farm buyItem(MarketRequest marketRequest) {
        logger.log(Level.INFO, String.format("%s is requesting to buy %s", marketRequest.getFarmName(), marketRequest.getItem()));
        Farm farm = farmService.getFarm(marketRequest.getFarmName());
        MarketItem marketItem = itemsToBuy.get(marketRequest.getItem());

        int priceOfTotalItems = marketItem.getBuyPrice() * marketRequest.getUnits();
        boolean result = false;
        if (farm.getBudget() >= priceOfTotalItems) {
            for (int i = 0; i < marketRequest.getUnits(); i++) {
                result = marketItem.addToFarm(farm);
            }
            farmService.updateFarm(farm);
        }
        messageUtil.sendMessage(marketRequest.getFarmName(), getMessage(marketRequest), result);
        return farm;
    }

    public Farm sellItem(MarketRequest marketRequest) {
        logger.log(Level.INFO, String.format("%s is requesting to sell %s", marketRequest.getFarmName(), marketRequest.getItem()));
        Farm farm = farmService.getFarm(marketRequest.getFarmName());
        MarketItem marketItem = itemsToSell.get(marketRequest.getItem());
        boolean result = false;
        for (int i = 0; i < marketRequest.getUnits(); i++) {
            result = marketItem.removeFromFarm(farm);
        }
        messageUtil.sendMessage(marketRequest.getFarmName(), "Sorry could'nt remove that item to your farm", result);

        farmService.updateFarm(farm);
        return farm;
    }

    private String getMessage(MarketRequest marketRequest) {
        String message;
        if (marketRequest.getItem().equals("field")) {
            message = "Sorry you could not afford to buy that field or there is no space for you to add another field";
        } else if (marketRequest.getItem().equals("cow") || marketRequest.getItem().equals("sheep") || marketRequest.getItem().equals("chicken")) {
            message = "Sorry you could not afford to buy that animal or there is no space to put the animal";
        } else {
            message = "Sorry you could not afford to buy that item";
        }
        return message;
    }
}
