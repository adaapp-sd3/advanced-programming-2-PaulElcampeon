class Farm {
    constructor() {
    }

    render(data) {

        try {
            getFarmContainer().removeChildren(0, getFarmContainer().children.length);
        } catch (err) {
            console.log("Cannot remove children in farm container as there are no children there");
        }

        let pettingFarmFields = 0;
        let growingFields = 0;
        let grazzingFields = 0;
        data.fields.forEach((field, indexOfFields) => {
            if (field.fieldType == "GROWING") {
                getGrowingFields()[growingFields].x = field.xpos * getResizeProperties().horizontalRatio;
                getGrowingFields()[growingFields].y = field.ypos * getResizeProperties().horizontalRatio;
                getGrowingFields()[growingFields].width = getResizeProperties().field.width;
                getGrowingFields()[growingFields].height = getResizeProperties().field.height;
                fieldListener(getGrowingFields()[growingFields], getFarmer(), indexOfFields);
                getFarmContainer().addChild(getGrowingFields()[growingFields]);
                growingFields++;
            } else if (field.fieldType == "GRAZZING") {
                getGrazzingFields()[grazzingFields].x = field.xpos * getResizeProperties().horizontalRatio;
                getGrazzingFields()[grazzingFields].y = field.ypos * getResizeProperties().horizontalRatio;
                getGrazzingFields()[grazzingFields].width = getResizeProperties().field.width;
                getGrazzingFields()[grazzingFields].height = getResizeProperties().field.height;
                fieldListener(getGrazzingFields()[grazzingFields], getFarmer(), indexOfFields);
                getFarmContainer().addChild(getGrazzingFields()[grazzingFields]);
                grazzingFields++;
            } else {
                getPettingFarmFields()[pettingFarmFields].x = field.xpos * getResizeProperties().horizontalRatio;
                getPettingFarmFields()[pettingFarmFields].y = field.ypos * getResizeProperties().horizontalRatio;
                getPettingFarmFields()[pettingFarmFields].width = getResizeProperties().field.width;
                getPettingFarmFields()[pettingFarmFields].height = getResizeProperties().field.height;
                fieldListener(getPettingFarmFields()[pettingFarmFields], getFarmer(), indexOfFields);
                getFarmContainer().addChild(getPettingFarmFields()[pettingFarmFields]);
                pettingFarmFields++;
            }
        });

        checkIfFarmerIsPresentOnFields(data.fields, getFarmer());

        let cow = 0;
        let sheep = 0;
        let chicken = 0;
        data.animals.forEach(animal => {
            if (animal.breed == "COW") {
                getCows()[cow].x = animal.xpos * getResizeProperties().horizontalRatio;
                getCows()[cow].y = animal.ypos * getResizeProperties().horizontalRatio;
                getCows()[cow].scale.set(getResizeProperties().animals.scale.cow);
                getFarmContainer().addChild(getCows()[cow]);
                cow++;
            } else if (animal.breed == "SHEEP") {
                getSheeps()[sheep].x = animal.xpos * getResizeProperties().horizontalRatio;
                getSheeps()[sheep].y = animal.ypos * getResizeProperties().horizontalRatio;
                getSheeps()[sheep].scale.set(getResizeProperties().animals.scale.sheep);
                getFarmContainer().addChild(getSheeps()[sheep]);
                sheep++;
            } else {//must be chicken
                getChickens()[chicken].x = animal.xpos * getResizeProperties().horizontalRatio;
                getChickens()[chicken].y = animal.ypos * getResizeProperties().horizontalRatio;
                getChickens()[chicken].scale.set(getResizeProperties().animals.scale.chicken);
                getFarmContainer().addChild(getChickens()[chicken]);
                chicken++;
            }
        });

        let corn = 0;
        let grass = 0;
        let wheat = 0;
        data.crops.forEach(crop => {
            if (crop.cropType == "GRASS") {
                getGrass()[grass].x = crop.xpos * getResizeProperties().horizontalRatio;
                getGrass()[grass].y = crop.ypos * getResizeProperties().horizontalRatio;
                getGrass()[grass].scale.set(getResizeProperties().crops.scale);
                getFarmContainer().addChild(getGrass()[grass]);
                grass++;
            } else if (crop.cropType == "WHEAT") {
                getWheat()[wheat].x = crop.xpos * getResizeProperties().horizontalRatio;
                getWheat()[wheat].y = crop.ypos * getResizeProperties().horizontalRatio;
                getWheat()[wheat].scale.set(getResizeProperties().crops.scale);
                getFarmContainer().addChild(getWheat()[wheat]);
                wheat++;
            } else {//must be corn
                getCorn()[corn].x = crop.xpos * getResizeProperties().horizontalRatio;
                getCorn()[corn].y = crop.ypos * getResizeProperties().horizontalRatio;
                getCorn()[corn].scale.set(getResizeProperties().crops.scale);
                getFarmContainer().addChild(getCorn()[corn]);
                corn++;
            }
        });

        playAnimalSounds(data);

        getStage().addChild(getFarmContainer());

        renderTourists();
    }
}
