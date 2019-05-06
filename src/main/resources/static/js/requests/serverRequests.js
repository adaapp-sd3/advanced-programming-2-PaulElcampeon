function sendMarketRequestBuy(data) {
    fetch('../market/buy', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
}

function sendMarketRequestSell(data) {
    fetch('../market/sell', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
}

function sendFarmModificationGrazzingField(data) {
    fetch('../farm/add/grazzing-field', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
}

function sendFarmModificationPettingFarmField(data) {
    fetch('../farm/add/petting-farm', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
}

function sendFarmModificationGrowingField(data) {
    fetch('../farm/add/growing-field', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
}

function sendRequestToMoveAnimal(data) {
    fetch('../farm/move/animal', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
}

function sendRequestToPlantSeed(data) {
    fetch('../farm/plant/seed', {
        method: 'post',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
}

function getCurrentWeather() {
    fetch('../get/weather', {
        method: 'get',
        headers: {
            'Accept': 'application/json'
        }
    })
    .then(response => response.json())
    .catch(error => console.error('Error:', error))
    .then(response => {
        setWeather(response.weather);
        }
    )
}