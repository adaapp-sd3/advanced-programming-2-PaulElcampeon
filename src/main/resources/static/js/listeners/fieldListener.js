var indexOfField;

const fieldListener = (field, farmer, indexOfField) => {
    if (farmer.x >= (field.xpos * getResizeProperties().horizontalRatio) && farmer.x <= ((field.xpos + field.width) * getResizeProperties().horizontalRatio) && (farmer.y) >= (field.ypos * getResizeProperties().horizontalRatio) && (farmer.y + farmer.height) <=  ((field.ypos + field.height) * getResizeProperties().horizontalRatio)) {
        setIndexOfField(indexOfField);
    }
};

function setIndexOfField(number) {
    indexOfField = number;
}

function getIndexOfField() {
    return indexOfField;
}

function checkIfFarmerIsPresentOnFields(fields, farmer) {
    let growingField = false;
    let grazzingField = false;
    let pettingField = false;
    let market = false;
    let activeFieldOptions;
    for (let i = 0; i < fields.length; i++) {
        if (farmer.x >= (fields[i].xpos * getResizeProperties().horizontalRatio) && farmer.x <= ((fields[i].xpos + fields[i].width) * getResizeProperties().horizontalRatio) && (farmer.y) >= (fields[i].ypos * getResizeProperties().horizontalRatio) && (farmer.y + farmer.height) <=  ((fields[i].ypos + fields[i].height) * getResizeProperties().horizontalRatio)) {
            activeFieldOptions = checkIfFieldIsAType(fields[i], grazzingField, growingField, pettingField);
            break;
        }
        activeFieldOptions = {grow: false, grazz: false, petting: false};
    }
    market = marketListener(getMarket(), getFarmer());
    displayAllTrueDivs(activeFieldOptions.grow, activeFieldOptions.grazz, activeFieldOptions.petting, market);
}

function displayAllTrueDivs(growingField, grazzingField, pettingField, market) {
    if (growingField) {
        getPettingFarmOptions().style.display = "none";
        getGrazzingOptions().style.display = "none";
        getGrowingOptions().style.display = "block";
        getFieldOptions().style.display = "block";
        getMarketOptions1().style.display = "none";
        getDashboard().div.style.display = "none";
    } else if (grazzingField) {
        getPettingFarmOptions().style.display = "none";
        getGrazzingOptions().style.display = "block";
        getGrowingOptions().style.display = "none";
        getFieldOptions().style.display = "block";
        getMarketOptions1().style.display = "none";
        getDashboard().div.style.display = "none";
    } else if (pettingField) {
        getPettingFarmOptions().style.display = "block";
        getGrazzingOptions().style.display = "none";
        getGrowingOptions().style.display = "none";
        getFieldOptions().style.display = "block";
        getMarketOptions1().style.display = "none";
        getDashboard().div.style.display = "none";
    } else if (market) {
        getMarketOptions1().style.display = "block";
        getPettingFarmOptions().style.display = "none";
        getGrazzingOptions().style.display = "none";
        getGrowingOptions().style.display = "none";
        getFieldOptions().style.display = "none";
        getDashboard().div.style.display = "none";
    } else if (!market && !pettingField && !grazzingField && !growingField) {
        getDashboard().div.style.display = "block";
        getMarketOptions1().style.display = "none";
        getPettingFarmOptions().style.display = "none";
        getGrazzingOptions().style.display = "none";
        getGrowingOptions().style.display = "none";
        getFieldOptions().style.display = "none";
    }
}