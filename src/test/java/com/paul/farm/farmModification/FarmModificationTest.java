package com.paul.farm.farmModification;

import com.paul.farm.dtos.FarmModificationRequestDto;
import com.paul.farm.dtos.PlantSeedRequestDto;
import com.paul.farm.models.Farm;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.models.fields.GrowingField;
import com.paul.farm.models.fields.PettingFarmField;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@Ignore
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class FarmModificationTest {

    @Mock
    private FarmService farmService;

    @InjectMocks
    private FarmModification farmModification;

    private Farm farm;

    @Before
    public void setUp() {
        farm = new Farm();
        farm.setFields(Field.getStartingFields());
        when(farmService.getFarm(Mockito.anyString())).thenReturn(farm);
    }


    @Test
    public void addPettingFarm() {
        FarmModificationRequestDto farmModificationRequestDto = new FarmModificationRequestDto("steve", 0);
        farmModification.addPettingFarm(farmModificationRequestDto);
        assertTrue("Petting farm should be in first in the farm fields list", farm.getFields().get(0) instanceof PettingFarmField);
        assertTrue("Petting farm x coordinate does not equal 0", farm.getFields().get(0).getXPos() != 0);
        assertTrue("Petting farm y coordinates are not 0", farm.getFields().get(0).getYPos() != 0);
    }

    @Test
    public void addGrazingField() {
        FarmModificationRequestDto farmModificationRequestDto = new FarmModificationRequestDto("steve", 0);
        farmModification.addGrazingField(farmModificationRequestDto);
        assertTrue("Petting farm should be in first in the farm fields list", farm.getFields().get(0) instanceof GrazingField);
        assertTrue("Petting farm x coordinate does not equal 0", farm.getFields().get(0).getXPos() != 0);
        assertTrue("Petting farm y coordinates are not 0", farm.getFields().get(0).getYPos() != 0);
    }

    @Test
    public void addGrowingField() {
        FarmModificationRequestDto farmModificationRequestDto = new FarmModificationRequestDto("steve", 0);
        farmModification.addGrowingField(farmModificationRequestDto);
        assertTrue("Petting farm should be in first in the farm fields list", farm.getFields().get(0) instanceof GrowingField);
        assertTrue("Petting farm x coordinate does not equal 0", farm.getFields().get(0).getXPos() != 0);
        assertTrue("Petting farm y coordinates are not 0", farm.getFields().get(0).getYPos() != 0);
    }

    @Test
    public void addSeed_true() {
        PlantSeedRequestDto plantSeedRequestDto = new PlantSeedRequestDto("steve", 0, "corn");
        farm.setCornSeed(4);
        farm.getFields().add(0, new GrowingField());
        farmModification.addSeed(plantSeedRequestDto);
        assertEquals("Growing fieldIndex should have 1 corn seed in it", 1, farm.getCrops().size());
        assertEquals("Number of corn seeds should now be 3", 3, farm.getCornSeed());
    }

    @Test
    public void addSeed_false() {
        PlantSeedRequestDto plantSeedRequestDto = new PlantSeedRequestDto("steve", 0, "corn");
        farm.setCornSeed(0);
        farm.getFields().add(0, new GrowingField());
        farmModification.addSeed(plantSeedRequestDto);
        assertEquals("Growing fieldIndex should have 0 crops", 0, farm.getCrops().size());
        assertEquals("Number of corn seeds should be 0", 0, farm.getCornSeed());
    }
}
