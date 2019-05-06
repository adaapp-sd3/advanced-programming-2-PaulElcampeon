package com.paul.farm.market;

import com.paul.farm.dtos.MarketRequest;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Chicken;
import com.paul.farm.models.animals.Cow;
import com.paul.farm.models.animals.Sheep;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.services.interfaces.FarmService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Ignore
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class MarketTest {

    @Mock
    private FarmService farmService;

    @InjectMocks
    private Market market;

    private Farm farm;

    @Before
    public void setUp() {
        farm = new Farm();
        farm.getFields().add(0, new GrazingField());
        farm.setBudget(3000);
        farm.getAnimals().addAll(Arrays.asList(new Cow(), new Sheep(), new Chicken()));
        farm.getFields().get(0).addToField(farm.getAnimals().get(0));
        farm.getFields().get(0).addToField(farm.getAnimals().get(1));
        farm.getFields().get(0).addToField(farm.getAnimals().get(2));
        farm.setTotalCows(1);
        farm.setTotalSheep(1);
        farm.setTotalChickens(1);
    }

    @Test
    public void sellItem_cow_true() {
        when(farmService.getFarm(Mockito.anyString())).thenReturn(farm);
        MarketRequest marketRequest = new MarketRequest("Steve", "cow", 1);
        Farm result = market.sellItem(marketRequest);
        assertEquals("farm should have 0 cows", 0, result.getTotalCows());
        assertEquals("farm should have only 2 animals", 2, result.getAnimals().size());
        assertEquals("farm should now have 3120", 3120, result.getBudget());
    }

    @Test
    public void buyItem_cow_true() {
        when(farmService.getFarm(Mockito.anyString())).thenReturn(farm);
        MarketRequest marketRequest = new MarketRequest("Steve", "cow", 1);
        Farm result = market.buyItem(marketRequest);
        assertEquals("farm should have 2 cows", 2, result.getTotalCows());
        assertEquals("farm should have only 4 animals", 4, result.getAnimals().size());
        assertEquals("farm should now have 2850", 2850, result.getBudget());

    }

    @Test
    public void sellItem_cow_false() {
        when(farmService.getFarm(Mockito.anyString())).thenReturn(farm);
        farm.getAnimals().remove(0);//removing the cow
        farm.setTotalCows(0);
        MarketRequest marketRequest = new MarketRequest("Steve", "cow", 1);
        Farm result = market.sellItem(marketRequest);
        assertEquals("farm should have 0 cows", 0, result.getTotalCows());
        assertEquals("farm should still have 2 animals", 2, result.getAnimals().size());
        assertEquals("farm should now have 3000", 3000, result.getBudget());
    }

    @Test
    public void buyItem_cow_false() {
        when(farmService.getFarm(Mockito.anyString())).thenReturn(farm);
        farm.setBudget(1);
        MarketRequest marketRequest = new MarketRequest("Steve", "cow", 1);
        Farm result = market.buyItem(marketRequest);
        assertEquals("farm should have 1 cows", 1, result.getTotalCows());
        assertEquals("farm should still have 3 animals", 3, result.getAnimals().size());
        assertEquals("farm should now have 1", 1, result.getBudget());
    }
}
