package com.paul.farm.animal;

import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.animals.Cow;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.utils.AnimalUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AnimalMovementTest {

    private Animal animal;
    private GrazingField field;

    @Before
    public void setUp() {
        animal = new Cow(5, 3);
        field = new GrazingField(0, 0);
        animal.setFieldIndex(0);
    }

    @Test
    public void moveAnimalLeft_True() {
        AnimalUtil.moveLeft(animal, field);
        assertEquals("animal xPos should be 4", 4, animal.getXPos());
    }

    @Test
    public void moveAnimalLeft_False() {
        animal.setXPos(1);
        AnimalUtil.moveLeft(animal, field);
        assertEquals("animal xPos should be 1", 1, animal.getXPos());
    }

    @Test
    public void moveAnimalRight_True() {
        animal.setXPos(298);
        AnimalUtil.moveRight(animal, field);
        assertEquals("animal xPos should be 299", 299, animal.getXPos());
    }

    @Test
    public void moveAnimalRight_False() {
        animal.setXPos(299);
        AnimalUtil.moveRight(animal, field);
        assertEquals("animal xPos should be 299", 299, animal.getXPos());
    }

    @Test
    public void moveAnimalUp_True() {
        animal.setYPos(2);
        AnimalUtil.moveUp(animal, field);
        assertEquals("animal yPos should be 1", 1, animal.getYPos());
    }

    @Test
    public void moveAnimalUp_False() {
        animal.setYPos(1);
        AnimalUtil.moveUp(animal, field);
        assertEquals("animal xPos should be 1", 1, animal.getYPos());
    }

    @Test
    public void moveAnimalDown_True() {
        animal.setYPos(198);
        AnimalUtil.moveDown(animal, field);
        assertEquals("animal yPos should be 199", 199, animal.getYPos());
    }

    @Test
    public void moveAnimalDown_False() {
        animal.setYPos(199);
        AnimalUtil.moveDown(animal, field);
        assertEquals("animal yPos should be 199", 199, animal.getYPos());
    }
}
