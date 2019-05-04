const fieldListener = (field, farmer, indexOfField) => {
    if (farmer.x >= field.getBounds().x && farmer.x <= field.getBounds().x + field.getBounds().width && farmer.y <= field.getBounds().y + field.getBounds().height && farmer.y >=  field.getBounds().y) {
        console.log("You are driving on me, my index is " + indexOfField);
        setIndexOfField(indexOfField);
    }
}

const marketListener = (market, farmer) => {
    if (farmer.x + farmer.width >= market.x && farmer.x <= market.x + market.width && farmer.y <= market.y + market.height && farmer.y >=  market.y - 30) {
        console.log("You have entered the market");
        if (!getMarketSound().playing()) {
            playMarketSound();
        }
        return true;
    }
    if (getMarketSound().playing()) {
        decreaseMarketVolume();
    }
    return false
}

var indexOfField;

function setIndexOfField(number) {
    indexOfField = number;
    console.log("Index of field has been set to " + number);
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
        if (farmer.x >= fields[i].xpos && farmer.x <= fields[i].xpos + fields[i].width && farmer.y + 20 <= fields[i].ypos + fields[i].height  && farmer.y >=  fields[i].ypos - 5) {
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