package com.paul.farm.animal;

import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.animals.Cow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AnimalGrowableTest {

    private Animal animal;

    @Before
    public void setUp() {
        animal = new Cow();
    }

    @Test
    public void growable_Level1() {
        animal.setBirthDate(System.currentTimeMillis() - 60000);
        assertEquals("animal growth Level should be 1", 1, animal.getGrowthLevel());
    }

    @Test
    public void growable_Level2() {
        animal.setBirthDate(System.currentTimeMillis() - 120001);
        animal.getGrowable().grow(animal);
        assertEquals("animal growth Level should be 2", 2, animal.getGrowthLevel());
    }

    @Test
    public void growable_Level3() {
        animal.setBirthDate(System.currentTimeMillis() - 240001);
        animal.getGrowable().grow(animal);
        assertEquals("animal growth Level should be 3", 3, animal.getGrowthLevel());
    }

    @Test
    public void growable_Level4() {
        animal.setBirthDate(System.currentTimeMillis() - 360001);
        animal.getGrowable().grow(animal);
        assertEquals("animal growth Level should be 4", 4, animal.getGrowthLevel());
    }
}
