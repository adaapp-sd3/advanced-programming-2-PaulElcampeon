function formFieldOptions() {
    let fieldOptionsDiv = document.createElement("div");

    let convertToGrazzingFieldBtn = document.createElement("button");
    convertToGrazzingFieldBtn.innerHTML = "Convert To Grazzing Field";
    convertToGrazzingFieldBtn.addEventListener("click", () => {
        let farmModificationData = {"farmName":getFarmName(), "fieldIndex":getIndexOfField()};
        sendFarmModificationGrazzingField(farmModificationData);
    })

    let convertToGrowingFieldBtn = document.createElement("button");
    convertToGrowingFieldBtn.innerHTML = "Convert To Growing Field";
    convertToGrowingFieldBtn.addEventListener("click", () => {
        let farmModificationData = {"farmName":getFarmName(), "fieldIndex":getIndexOfField()};
        sendFarmModificationGrowingField(farmModificationData);
    })

    let convertToPettingFarmFieldBtn = document.createElement("button");
    convertToPettingFarmFieldBtn.innerHTML = "Convert To Petting Farm Field";
    convertToPettingFarmFieldBtn.addEventListener("click", () => {
        let farmModificationData = {"farmName":getFarmName(), "fieldIndex":getIndexOfField()};
        sendFarmModificationPettingFarmField(farmModificationData);
    })

    convertToGrazzingFieldBtn.classList.add('my-4', 'forDashboardBtn', 'w-75');
    convertToGrowingFieldBtn.classList.add('my-4', 'forDashboardBtn', 'w-75');
    convertToPettingFarmFieldBtn.classList.add('my-4', 'forDashboardBtn', 'w-75');

    fieldOptionsDiv.appendChild(convertToGrazzingFieldBtn);
    fieldOptionsDiv.appendChild(convertToGrowingFieldBtn);
    fieldOptionsDiv.appendChild(convertToPettingFarmFieldBtn);

    fieldOptionsDiv.style.display = "none";

    return fieldOptionsDiv;
}

function grazzingFieldOptions() {
    let grazzingFieldMenu = document.createElement("div");
    grazzingFieldMenu.appendChild(getMoveAnimalButton("Move cow to petting farm", "cow", "PETTINGFARM"));
    grazzingFieldMenu.appendChild(getMoveAnimalButton("Move sheep to petting farm", "sheep", "PETTINGFARM"));
    grazzingFieldMenu.appendChild(getMoveAnimalButton("Move chicken to petting farm", "chicken", "PETTINGFARM"));
    grazzingFieldMenu.style.display = "none";
    return grazzingFieldMenu;
}

function growingFieldOptions() {
    let growingFieldMenu = document.createElement("div");
    growingFieldMenu.appendChild(getPlantSeedButton("Plant corn seed", "corn"));
    growingFieldMenu.appendChild(getPlantSeedButton("Plant wheat seed", "wheat"));
    growingFieldMenu.appendChild(getPlantSeedButton("Plant grass seed", "grass"));
    growingFieldMenu.style.display = "none";
    return growingFieldMenu;
}

function pettingFarmFieldOptions() {
    let pettingFarmFieldMenu = document.createElement("div");
    pettingFarmFieldMenu.appendChild(getMoveAnimalButton("Move cow to grazzing field", "cow", "GRAZZING"));
    pettingFarmFieldMenu.appendChild(getMoveAnimalButton("Move chicken to grazzing field", "chicken", "GRAZZING"));
    pettingFarmFieldMenu.appendChild(getMoveAnimalButton("Move sheep to grazzing field", "sheep", "GRAZZING"));
    pettingFarmFieldMenu.style.display = "none";
    return pettingFarmFieldMenu;
}

function getMoveAnimalButton(buttonText, animal, fieldType) {
    let tempButton = document.createElement("button");
    tempButton.classList.add('my-3', 'forDashboardBtn', 'w-75');
    tempButton.innerHTML = buttonText;
    tempButton.addEventListener("click", () => {
        let moveAnimalDto = {"farmName":getFarmName(), "animal":animal, "fieldType":fieldType, "fieldIndex": getIndexOfField()}
        sendRequestToMoveAnimal(moveAnimalDto);
    })
    return tempButton;
}

function getPlantSeedButton(buttonText, seed) {
    let tempButton = document.createElement("button");
    tempButton.classList.add('my-3', 'forDashboardBtn', 'w-75');
    tempButton.innerHTML = buttonText;
    tempButton.addEventListener("click", () => {
        let plantSeedRequestDto = {"farmName":getFarmName(), "fieldIndex": getIndexOfField(), "seed":seed}
        sendRequestToPlantSeed(plantSeedRequestDto);
    })
    return tempButton;
}

var fieldOptions = formFieldOptions();

var grazzingOptions = grazzingFieldOptions();

var growingOptions = growingFieldOptions();

var pettingFarmOptions = pettingFarmFieldOptions();

function getFieldOptions() {
    return fieldOptions;
}

function getGrazzingOptions() {
    return grazzingOptions;
}

function getGrowingOptions() {
    return growingOptions;
}

function getPettingFarmOptions() {
    return pettingFarmOptions;
}

function checkIfFieldIsAType(field, grazzingField, growingField, pettingField) {
    if (field.fieldType == "GRAZZING") {
        grazzingField = true;
    } else if (field.fieldType == "GROWING") {
        growingField = true;
    } else if (field.fieldType == "PETTINGFARM") {
        pettingField = true;
    }
    return {
        grazz: grazzingField, grow: growingField, petting:pettingField
    }
}