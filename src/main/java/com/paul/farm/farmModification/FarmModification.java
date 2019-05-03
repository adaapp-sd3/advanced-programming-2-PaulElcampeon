package com.paul.farm.farmModification;

import com.paul.farm.dtos.FarmModificationRequestDto;
import com.paul.farm.dtos.MoveAnimalDto;
import com.paul.farm.dtos.PlantSeedRequestDto;
import com.paul.farm.models.Farm;
import com.paul.farm.models.animals.Animal;
import com.paul.farm.models.animals.Cow;
import com.paul.farm.models.crops.Corn;
import com.paul.farm.models.crops.Crop;
import com.paul.farm.models.crops.Grass;
import com.paul.farm.models.crops.Wheat;
import com.paul.farm.models.fields.Field;
import com.paul.farm.models.fields.GrazingField;
import com.paul.farm.models.fields.GrowingField;
import com.paul.farm.models.fields.PettingFarmField;
import com.paul.farm.services.interfaces.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class FarmModification {

    @Autowired
    private FarmService farmService;

    private Logger logger = Logger.getLogger(FarmModification.class.getName());

    public Farm addPettingFarm(FarmModificationRequestDto farmModificationRequestDto) {
        logger.log(Level.INFO, String.format("%s has made a request to add a petting farm in fieldIndex %d",
                farmModificationRequestDto.getFarmName(),
                farmModificationRequestDto.getFieldIndex()));
        Farm farm = farmService.getFarm(farmModificationRequestDto.getFarmName());
        Field previousField = farm.getFields().get(farmModificationRequestDto.getFieldIndex());
        PettingFarmField pettingFarmField = new PettingFarmField(previousField.getXPos(), previousField.getYPos());
        farm.getFields().add(farmModificationRequestDto.getFieldIndex(), pettingFarmField);
        farm.getFields().remove(farmModificationRequestDto.getFieldIndex() + 1);
        farmService.updateFarm(farm);
        return farm;
    }

    public Farm addGrazingField(FarmModificationRequestDto farmModificationRequestDto) {
        logger.log(Level.INFO, String.format("%s has made a request to add a grazzing fieldIndex in fieldIndex %d",
                farmModificationRequestDto.getFarmName(),
                farmModificationRequestDto.getFieldIndex()));
        Farm farm = farmService.getFarm(farmModificationRequestDto.getFarmName());
        Field previousField = farm.getFields().get(farmModificationRequestDto.getFieldIndex());
        GrazingField grazingField = new GrazingField(previousField.getXPos(), previousField.getYPos());
        farm.getFields().add(farmModificationRequestDto.getFieldIndex(), grazingField);
        farm.getFields().remove(farmModificationRequestDto.getFieldIndex() + 1);
        farmService.updateFarm(farm);
        return farm;
    }

    public Farm addGrowingField(FarmModificationRequestDto farmModificationRequestDto) {
        logger.log(Level.INFO, String.format("%s has made a request to add a growing fieldIndex in fieldIndex %d",
                farmModificationRequestDto.getFarmName(),
                farmModificationRequestDto.getFieldIndex()));
        Farm farm = farmService.getFarm(farmModificationRequestDto.getFarmName());
        Field previousField = farm.getFields().get(farmModificationRequestDto.getFieldIndex());
        GrowingField growingField = new GrowingField(previousField.getXPos(), previousField.getYPos());
        farm.getFields().add(farmModificationRequestDto.getFieldIndex(), growingField);
        farm.getFields().remove(farmModificationRequestDto.getFieldIndex() + 1);
        farmService.updateFarm(farm);
        return farm;
    }

    public boolean addSeed(PlantSeedRequestDto plantSeedRequestDto) {
        logger.log(Level.INFO, String.format("%s has made a request to plant a %s seed in field at position %d",
                plantSeedRequestDto.getFarmName(),
                plantSeedRequestDto.getSeed(),
                plantSeedRequestDto.getFieldIndex()));
//                plantSeedRequestDto.getX(),
//                plantSeedRequestDto.getY()));
        Farm farm = farmService.getFarm(plantSeedRequestDto.getFarmName());
        Crop crop = null;
        if (plantSeedRequestDto.getSeed().equals("corn")) {
            if (farm.getCornSeed() > 0) {
//                crop = new Corn(plantSeedRequestDto.getX(), plantSeedRequestDto.getY());
                crop = new Corn();

                farm.setCornSeed(farm.getCornSeed() - 1);
            } else {
                return false;
            }
        }
        if (plantSeedRequestDto.getSeed().equals("grass")) {
            if (farm.getGrassSeed() > 0) {
//                crop = new Grass(plantSeedRequestDto.getX(), plantSeedRequestDto.getY());
                crop = new Grass();
                farm.setGrassSeed(farm.getGrassSeed() - 1);
            } else {
                return false;
            }
        }
        if (plantSeedRequestDto.getSeed().equals("wheat")) {
            if (farm.getWheatSeed() > 0) {
//                crop = new Wheat(plantSeedRequestDto.getX(), plantSeedRequestDto.getY());
                crop = new Wheat();
                farm.setWheatSeed(farm.getWheatSeed() - 1);
            } else {
                return false;
            }
        }
        crop.setIndexOfField(plantSeedRequestDto.getFieldIndex());
        farm.getFields().get(plantSeedRequestDto.getFieldIndex()).addToField(crop);
        farm.getCrops().add(crop);
        farmService.updateFarm(farm);
        return true;
    }

    public boolean moveAnimal(MoveAnimalDto moveAnimalDto) {
        logger.log(Level.INFO, String.format("%s has made a request to move a %s to a %s from field in position %d",
                moveAnimalDto.getFarmName(),
                moveAnimalDto.getAnimal(),
                moveAnimalDto.getFieldType(),
                moveAnimalDto.getFieldIndex()));
        Farm farm = farmService.getFarm(moveAnimalDto.getFarmName());
        for (int i = 0; i < farm.getAnimals().size(); i++) {
            if (farm.getAnimals().get(i).getFieldIndex() == moveAnimalDto.getFieldIndex() && farm.getAnimals().get(i).getBreed().toString().equals(moveAnimalDto.getAnimal().toUpperCase())) {
                for (int j = 0; j < farm.getFields().size(); j++) {
                    if (farm.getFields().get(j).getFieldType().toString().equals(moveAnimalDto.getFieldType()) && farm.getFields().get(j).getNoOfAnimals() < 20) {
                        //getting the field that the animal is already in and reducing the number of animals by 1
                        farm.getFields().get(farm.getAnimals().get(i).getFieldIndex()).setNoOfAnimals(farm.getFields().get(farm.getAnimals().get(i).getFieldIndex()).getNoOfAnimals() - 1);
                        farm.getAnimals().get(i).setFieldIndex(j);
                        //getting the field that the animal is now in and increasing the number of animals by 1
                        farm.getFields().get(j).setNoOfAnimals(farm.getFields().get(j).getNoOfAnimals() + 1);
                        farm.getFields().get(j).addToField(farm.getAnimals().get(i));
                        farmService.updateFarm(farm);
                        return true;
                    }
                }
            }
        }
        farmService.updateFarm(farm);
        return false;
    }
}
