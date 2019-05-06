package com.paul.farm.animal;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.animals.Cow;
import com.paul.farm.models.fields.GrazingField;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@Ignore
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AnimalMaturableTest {

    private Animal animal;
    private Farm farm;

    @Before
    public void setUp() {
        animal = new Cow();
        farm = new Farm();
        farm.setFields(Arrays.asList(new GrazingField()));
        farm.getFields().get(0).addToField(animal);
        farm.getAnimals().add(animal);
        farm.setTotalCows(1);
    }

    @Test
    public void maturable_mature_true() {
        animal.setBirthDate(System.currentTimeMillis() - 600000l);
        animal.getMaturable().mature(farm, animal);
        assertEquals("we should have 1 beef in farm", 1, farm.getTotalBeef());
        assertEquals("we should have 0 cows in farm", 0, farm.getTotalCows());
        assertFalse("cow should no longer be in farm", farm.getAnimals().contains(animal));
        assertTrue("cow should be dead", animal.isDead());
    }

    @Test
    public void maturable_mature_false() {
        animal.setBirthDate(System.currentTimeMillis() - 400000l);
        animal.getMaturable().mature(farm, animal);
        assertEquals("we should have 0 beef in farm", 0, farm.getTotalBeef());
        assertEquals("we should have 1 cows in farm", 1, farm.getTotalCows());
        assertTrue("cow should still be in farm", farm.getAnimals().contains(animal));
        assertFalse("cow should still be be alive", animal.isDead());
    }
}
