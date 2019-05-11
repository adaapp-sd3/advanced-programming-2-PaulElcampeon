const growingFields = [];
const grazzingFields = [];
const pettingFarmFields = [];
const cows = [];
const sheeps = [];
const chickens = [];
const visitors = [];
const wheat = [];
const grass = [];
const corn = [];
const leftTractorTexture = new PIXI.Texture.from("../images/tractor/tractorLeft.png");
const rightTractorTexture = new PIXI.Texture.from("../images/tractor/tractorRight.png");
const marketTexture = new PIXI.Texture.from("../images/building/market.png");
const farmer = new PIXI.Sprite(leftTractorTexture);
const market = new PIXI.Sprite(marketTexture);
const marketOriginalPos = {x:500, y:600};
const tractorOriginalPos = {x:100, y:100};

function createFields() {
    return new Promise((resolve, reject) => {
        for (let i = 0; i < 5; i++) {
            getGrowingFields().push(createFieldStash("GROWING", 0xb58969));
        }
        for (let i = 0; i < 5; i++) {
            getGrazzingFields().push(createFieldStash("GRAZZING", 0x66CD00));
        }
        for (let i = 0; i < 5; i++) {
            getPettingFarmFields().push(createFieldStash("PETTINGFARM", 0xDEB887));
        }
        resolve();
    })
}

function createAnimals() {
    return new Promise((resolve, reject) => {
        for (let i = 0; i < 100; i++) {
            getCows().push(createAnimalStash("COW", getResizeProperties().animals.cow));
        }
        for (let i = 0; i < 100; i++) {
            getChickens().push(createAnimalStash("CHICKEN", getResizeProperties().animals.chicken));
        }
        for (let i = 0; i < 100; i++) {
            getSheeps().push(createAnimalStash("SHEEP", getResizeProperties().animals.sheep));
        }
        resolve();
    })
}

function createCrops() {
    return new Promise((resolve, reject) => {
        for (let i = 0; i < 100; i++) {
            getWheat().push(createCropStash("WHEAT", getResizeProperties().crops.scale));
        }
        for (let i = 0; i < 100; i++) {
            getCorn().push(createCropStash("CORN", getResizeProperties().crops.scale));
        }
        for (let i = 0; i < 100; i++) {
            getGrass().push(createCropStash("GRASS", getResizeProperties().crops.scale));
        }
        resolve();
    })
}

function createVisitors() {
    return new Promise((resolve, reject) => {
        for (let i = 0; i < 100; i++) {
            let visitor = new PIXI.Sprite.from("../images/people/visitor.png");
            visitor.scale.set(getResizeProperties().visitors.scale);
            visitor.x = 0;
            visitor.y = 0;
            visitor.name = "visitor";
            getVisitors().push(visitor);
        }
        resolve();
    })
}

function createTractorAndMarketSprite() {
    market.name = "market";
    farmer.name = "farmer";
    market.x = 500 * getResizeProperties().horizontalRatio;
    market.y = 600 * getResizeProperties().horizontalRatio;
    farmer.x = 100 * getResizeProperties().horizontalRatio;
    farmer.y = 100 * getResizeProperties().horizontalRatio;
    farmer.vx = 0;
    farmer.vy = 0;
    farmer.anchor.set(0.5);
    farmer.scale.set(getResizeProperties().tractor.scale);
}

function createCropStash(cropType, scale) {
    let cropSprite = new PIXI.Sprite.from("../images/crops/" + cropType + ".png");
    cropSprite.scale.set(scale);
    cropSprite.x = 0;
    cropSprite.y = 0;
    cropSprite.name = "crop";
    return cropSprite
}

function createFieldStash(fieldType, colorCode) {
    let newField = new PIXI.Graphics();
    newField.name = fieldType;
    newField.lineStyle(8, 0x7c4011, 1);
    newField.beginFill(colorCode, 1);
    newField.drawRoundedRect(0, 0, getResizeProperties().field.width, getResizeProperties().field.height, 16);
    newField.endFill();
    return newField;
}

function createAnimalStash(animalBreed, scale) {
    let animalSprite = new PIXI.Sprite.from("../images/animals/" + animalBreed + ".png");
    animalSprite = modifyAnimals(animalBreed, animalSprite, scale);
    animalSprite.x = 0;
    animalSprite.y = 0;
    animalSprite.name = animalBreed;
    return animalSprite;
}

function modifyAnimals(animalBreed, animalSprite, scale) {
    if (animalBreed == "CHICKEN") {
        animalSprite.scale.set(scale);
    } else if (animalBreed == "COW") {
        animalSprite.scale.set(scale);
    } else {
        animalSprite.scale.set(scale);
    }
    return animalSprite;
}

function getGrazzingFields() {
    return grazzingFields;
}

function getGrowingFields() {
    return growingFields;
}

function getPettingFarmFields() {
    return pettingFarmFields;
}

function getCows() {
    return cows;
}

function getSheeps() {
    return sheeps;
}

function getChickens() {
    return chickens;
}

function getVisitors() {
    return visitors;
}

function getGrass() {
    return grass;
}

function getWheat() {
    return wheat;
}

function getCorn() {
    return corn;
}

function getFarmer() {
    return farmer;
}

function getMarket() {
    return market;
}

function getMarketTexture() {
    return marketTexture;
}

function getLeftTractorTexture() {
    return leftTractorTexture;
}

function getRightTractorTexture() {
    return rightTractorTexture;
}

function getOriginalMarketPos() {
    return marketOriginalPos;
}

function getOriginalTractorPos() {
    return tractorOriginalPos;
}