package com.paul.farm.animal;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.animals.Cow;
import com.paul.farm.models.fields.GrazingField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AnimalDeathableTest {

    private Animal animal;
    private Farm farm;

    @Before
    public void setUp() {
        animal = new Cow();
        farm = new Farm();
        farm.getFields().add(0, new GrazingField());
        farm.getAnimals().add(animal);
        farm.getFields().get(0).addToField(animal);
    }

    @Test
    public void deathable_checkIfDeadTrue() {
        animal.setHungerLevel(0);
        animal.getDeathable().checkIfDead(farm, animal);
        assertFalse("Should return false as cow no longer in farm", farm.getAnimals().contains(animal));
        assertTrue("Animal should now be dead", animal.isDead());
    }

    @Test
    public void deathable_checkIfDeadFalse() {
        animal.setHungerLevel(5);
        animal.getDeathable().checkIfDead(farm, animal);
        assertTrue("Should return true as cow still in farm", farm.getAnimals().contains(animal));
        assertTrue("Animal should not be dead", animal.isDead());
    }
}
