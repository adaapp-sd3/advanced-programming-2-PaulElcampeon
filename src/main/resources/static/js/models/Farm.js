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
                getGrowingFields()[growingFields].x = field.xpos;
                getGrowingFields()[growingFields].y = field.ypos;
                fieldListener(getGrowingFields()[growingFields], getFarmer(), indexOfFields);
                getFarmContainer().addChild(getGrowingFields()[growingFields]);
                growingFields++;
            } else if (field.fieldType == "GRAZZING") {
                getGrazzingFields()[grazzingFields].x = field.xpos;
                getGrazzingFields()[grazzingFields].y = field.ypos;
                fieldListener(getGrazzingFields()[grazzingFields], getFarmer(), indexOfFields);
                getFarmContainer().addChild(getGrazzingFields()[grazzingFields]);
                grazzingFields++;
            } else {
                getPettingFarmFields()[pettingFarmFields].x = field.xpos;
                getPettingFarmFields()[pettingFarmFields].y = field.ypos;
                fieldListener(getPettingFarmFields()[pettingFarmFields], getFarmer(), indexOfFields);
                getFarmContainer().addChild(getPettingFarmFields()[pettingFarmFields]);
                pettingFarmFields++;
            }
        })

        checkIfFarmerIsPresentOnFields(data.fields, getFarmer());

        let cow = 0;
        let sheep = 0;
        let chicken = 0;
        data.animals.forEach(animal => {
            if (animal.breed == "COW") {
                getCows()[cow].x = animal.xpos;
                getCows()[cow].y = animal.ypos;
                getFarmContainer().addChild(getCows()[cow]);
                cow++;
            } else if (animal.breed == "SHEEP") {
                getSheeps()[sheep].x = animal.xpos;
                getSheeps()[sheep].y = animal.ypos;
                getFarmContainer().addChild(getSheeps()[sheep]);
                sheep++;
            } else {//must be chicken
                getChickens()[chicken].x = animal.xpos;
                getChickens()[chicken].y = animal.ypos;
                getFarmContainer().addChild(getChickens()[chicken]);
                chicken++;
            }
        })

        let corn = 0;
        let grass = 0;
        let wheat = 0;
        data.crops.forEach(crop => {
            if (crop.cropType == "GRASS") {
                getGrass()[grass].x = crop.xpos;
                getGrass()[grass].y = crop.ypos;
                getFarmContainer().addChild(getGrass()[grass]);
                grass++;
            } else if (crop.cropType == "WHEAT") {
                getWheat()[wheat].x = crop.xpos;
                getWheat()[wheat].y = crop.ypos;
                getFarmContainer().addChild(getWheat()[wheat]);
                wheat++;
            } else {//must be corn
                getCorn()[corn].x = crop.xpos;
                getCorn()[corn].y = crop.ypos;
                getFarmContainer().addChild(getCorn()[corn]);
                corn++;
            }
        })

        playAnimalSounds(data);

        getStage().addChild(getFarmContainer());

        renderTourists();
    }
}
