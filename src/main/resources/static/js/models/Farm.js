class Farm {
    constructor() {
    }

    render(data) {

        let newContainer = new PIXI.Container();

        let indexOfFields = 0;
        data.fields.forEach(field => {
            let newField = new PIXI.Graphics();
            newField.name = field.fieldType;
            newField.lineStyle(10, 0x7c4011, 1);
            if (field.fieldType == "GROWING") {
                newField.beginFill(0xb58969, 1);
            } else if (field.fieldType == "GRAZZING") {
                newField.beginFill(0x66CD00, 1);
            } else {
                newField.beginFill(0xDEB887, 1);
            }
            newField.drawRoundedRect(field.xpos, field.ypos, field.width, field.height, 16);
            newField.endFill();
            fieldListener(newField, getFarmer(), indexOfFields)
            newContainer.addChild(newField);
            indexOfFields++;
        })

        checkIfFarmerIsPresentOnFields(data.fields, getFarmer());

        data.animals.forEach(animal => {
            let animalSprite = new PIXI.Sprite.from("../images/animals/"+animal.breed+".png");
            if (animal.growthLevel == 1) {
                animalSprite.scale.set(0.3);
            } else if (animal.growthLevel == 2) {
                animalSprite.scale.set(0.4);
            } else {
               animalSprite.scale.set(0.6);
            }

            if (animal.hungerLevel < 30) {
                animal.alpha = 0.3;
            }
            animalSprite.x = animal.xpos;
            animalSprite.y = animal.ypos;
            newContainer.addChild(animalSprite);
        })

        data.crops.forEach(crop => {
            let cropSprite = new PIXI.Sprite.from("../images/crops/"+crop.cropType+".png");
            cropSprite.scale.set(0.3);
            cropSprite.x = crop.xpos;
            cropSprite.y = crop.ypos;
            newContainer.addChild(cropSprite);
        })

        try {
            getStage().removeChildAt(1);
        } catch(err) {
            console.log(err);
        }

        getStage().addChild(newContainer);
    }
}