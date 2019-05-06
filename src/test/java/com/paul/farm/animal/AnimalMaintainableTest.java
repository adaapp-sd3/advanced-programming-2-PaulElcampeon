package com.paul.farm.animal;

import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.animals.Cow;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrazingField;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Ignore
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AnimalMaintainableTest {

    private Animal animal;
    private Farm farm;

    @Before
    public void setUp() {
        animal = new Cow();
        farm = new Farm();
        farm.setFields(Arrays.asList(new GrazingField()));
        farm.getFields().get(0).addToField(animal);
        farm.getAnimals().add(animal);
    }

    @Test
    public void maintainable_maintain_true() {
        animal.setNextMaintenance(System.currentTimeMillis() - 120000l);
        animal.getMaintainable().maintain(farm, animal);
        assertEquals("we should have 1 milk in farm", 1, farm.getTotalMilk());
        assertFalse("next maintenance time should not be reached", System.currentTimeMillis() - animal.getNextMaintenance() > 120000l);
    }

    @Test
    public void maintainable_maintain_false() {
        animal.setNextMaintenance(System.currentTimeMillis() - 60000l);
        animal.getMaintainable().maintain(farm, animal);
        assertEquals("we should have 0 milk in farm", 0, farm.getTotalMilk());
    }
}
