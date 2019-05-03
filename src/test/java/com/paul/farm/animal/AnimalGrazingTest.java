package com.paul.farm.animal;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.animals.Chicken;
import com.paul.farm.models.animals.Cow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AnimalGrazingTest {

    private Farm farm;
    private Animal animal;

    @Before
    public void setUp() {
        farm = new Farm();
        animal = new Cow();
    }

    @Test
    public void grazzingTest_StrawAvailable() {
        farm.setTotalStraw(5);
        animal.setHungerLevel(5);
        animal.getGrazable().graze(farm, animal);
        assertEquals("Farm straw should be 4", 4, farm.getTotalStraw());
        assertEquals("animal hunger level should be 6", 6, animal.getHungerLevel());
    }

    @Test
    public void grazzingTest_StrawUnavailable() {
        farm.setTotalStraw(0);
        animal.setHungerLevel(5);
        animal.getGrazable().graze(farm, animal);
        assertEquals("Farm straw should be 0", 0, farm.getTotalStraw());
        assertEquals("animal hunger level should be 4", 4, animal.getHungerLevel());
    }

    @Test
    public void grazzingTest_CornAvailable() {
        farm.setTotalCorn(5);
        animal = new Chicken();
        animal.setHungerLevel(5);
        animal.getGrazable().graze(farm, animal);
        assertEquals("Farm corn should be 4", 4, farm.getTotalCorn());
        assertEquals("animal hunger level should be 6", 6, animal.getHungerLevel());
    }

    @Test
    public void grazzingTest_CornUnavailable() {
        farm.setTotalCorn(0);
        animal = new Chicken();
        animal.setHungerLevel(5);
        animal.getGrazable().graze(farm, animal);
        assertEquals("Farm corn should be 0", 0, farm.getTotalCorn());
        assertEquals("animal hunger level should be 4", 4, animal.getHungerLevel());
    }
}
