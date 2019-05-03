class Dashboard {
    constructor() {
        this.rendered = false;
        this.div = document.createElement("div");
        this.div.classList.add('text-center', 'w-100','text-white', 'specialFont', 'my-1');
        this.div.style.fontSize = "1vw";

        this.header = document.createElement("p");
        this.header.classList.add('m-0', 'p-2', 'border', 'border-white')
        this.title = document.createElement("p");
        this.title.classList.add('underline', 'mt-3');

        this.budgetTitle = document.createElement("p");
        this.budgetTitle.classList.add('underline', 'mt-3');

        this.budget = document.createElement("p");

        this.seedsTitle = document.createElement("p");
        this.seedsTitle.classList.add('underline', 'mt-3');
        this.grassSeed = document.createElement("p");
        this.wheatSeed = document.createElement("p");
        this.cornSeed = document.createElement("p");

        this.produceTitle = document.createElement("p");
        this.produceTitle.classList.add('underline', 'mt-3');

        this.totalMilk = document.createElement("p");
        this.totalEggs = document.createElement("p");
        this.totalBeef = document.createElement("p");
        this.totalWool = document.createElement("p");
        this.totalLamb = document.createElement("p");
        this.totalStraw = document.createElement("p");
        this.totalBread = document.createElement("p");
        this.totalCorn = document.createElement("p");
        this.totalWheat = document.createElement("p");

        this.animalTitle = document.createElement("p");
        this.animalTitle.classList.add('underline', 'mt-3');

        this.totalChickens = document.createElement("p");
        this.totalCows = document.createElement("p");
        this.totalSheep = document.createElement("p");
    }

    render(data) {
        this.rendered = true;
        this.header.innerHTML = "DASHBOARD";
        this.title.innerHTML = "Farmer " + data.farmName;

        this.budgetTitle.innerHTML = "Budget"
        this.budget.innerHTML = data.budget;

        this.seedsTitle.innerHTML = "SEEDS";
        this.grassSeed.innerHTML = "Grass: " + data.grassSeed;
        this.wheatSeed.innerHTML = "Wheat: " + data.wheatSeed;
        this.cornSeed.innerHTML = "Corn: " + data.cornSeed;

        this.produceTitle.innerHTML = "PRODUCE";
        this.totalMilk.innerHTML = "Milk: " + data.totalMilk;
        this.totalEggs.innerHTML = "Eggs: " + data.totalEggs;
        this.totalBeef.innerHTML = "Beef: " + data.totalBeef;
        this.totalWool.innerHTML = "Wool: " + data.totalWool;
        this.totalLamb.innerHTML = "Lamb: " + data.totalLamb;
        this.totalStraw.innerHTML = "Straw: " + data.totalStraw;
        this.totalBread.innerHTML = "Bread: " + data.totalBread;
        this.totalCorn.innerHTML = "Corn: " + data.totalCorn;
        this.totalWheat.innerHTML = "Wheat: " + data.totalWheat;

        this.animalTitle.innerHTML = "ANIMALS";
        this.totalChickens.innerHTML = "Chicken: " + data.totalChickens;
        this.totalCows.innerHTML = "Cow: " + data.totalCows;
        this.totalSheep.innerHTML = "Sheep: " + data.totalSheep;

        this.appendElements();
    }

    update(data) {
        this.budget.innerHTML = "£" + data.budget;
        this.grassSeed.innerHTML = "Grass: " + data.grassSeed;
        this.wheatSeed.innerHTML = "Wheat: " + data.wheatSeed;
        this.cornSeed.innerHTML = "Corn: " + data.cornSeed;
        this.totalMilk.innerHTML = "Milk: " + data.totalMilk;
        this.totalEggs.innerHTML = "Eggs: " + data.totalEggs;
        this.totalBeef.innerHTML = "Beef: " + data.totalBeef;
        this.totalWool.innerHTML = "Wool: " + data.totalWool;
        this.totalLamb.innerHTML = "Lamb: " + data.totalLamb;
        this.totalStraw.innerHTML = "Straw: " + data.totalStraw;
        this.totalBread.innerHTML = "Bread: " + data.totalBread;
        this.totalCorn.innerHTML = "Corn: " + data.totalCorn;
        this.totalWheat.innerHTML = "Wheat: " + data.totalWheat;
        this.totalChickens.innerHTML = "Chicken: " + data.totalChickens;
        this.totalCows.innerHTML = "Cow: " + data.totalCows;
        this.totalSheep.innerHTML = "Sheep: " + data.totalSheep;
    }

    appendElements() {
        getDashboardDiv().appendChild(this.header);
        getDashboardDiv().appendChild(this.title);

        getDashboardDiv().appendChild(this.budgetTitle);
        getDashboardDiv().appendChild(this.budget);

//        getDashboardDiv().appendChild(this.seedsTitle);
//        getDashboardDiv().appendChild(this.grassSeed);
//        getDashboardDiv().appendChild(this.wheatSeed);
//        getDashboardDiv().appendChild(this.cornSeed);
//
//        getDashboardDiv().appendChild(this.produceTitle);
//
//        getDashboardDiv().appendChild(this.totalMilk);
//        getDashboardDiv().appendChild(this.totalEggs);
//        getDashboardDiv().appendChild(this.totalBeef);
//        getDashboardDiv().appendChild(this.totalWool);
//        getDashboardDiv().appendChild(this.totalLamb);
//        getDashboardDiv().appendChild(this.totalStraw);
//        getDashboardDiv().appendChild(this.totalBread);
//        getDashboardDiv().appendChild(this.totalCorn);
//        getDashboardDiv().appendChild(this.totalWheat);
//
//        getDashboardDiv().appendChild(this.animalTitle);
//        getDashboardDiv().appendChild(this.totalChickens);
//        getDashboardDiv().appendChild(this.totalCows);
//        getDashboardDiv().appendChild(this.totalSheep);

        this.div.appendChild(this.seedsTitle);
        this.div.appendChild(this.grassSeed);
        this.div.appendChild(this.wheatSeed);
        this.div.appendChild(this.cornSeed);

        this.div.appendChild(this.produceTitle);

        this.div.appendChild(this.totalMilk);
        this.div.appendChild(this.totalEggs);
        this.div.appendChild(this.totalBeef);
        this.div.appendChild(this.totalWool);
        this.div.appendChild(this.totalLamb);
        this.div.appendChild(this.totalStraw);
        this.div.appendChild(this.totalBread);
        this.div.appendChild(this.totalCorn);
        this.div.appendChild(this.totalWheat);

        this.div.appendChild(this.animalTitle);
        this.div.appendChild(this.totalChickens);
        this.div.appendChild(this.totalCows);
        this.div.appendChild(this.totalSheep);

        getDashboardDiv().appendChild(this.div);

        getDashboardDiv().appendChild(getMarketOptions1());
        getDashboardDiv().appendChild(getGrazzingOptions());
        getDashboardDiv().appendChild(getGrowingOptions());
        getDashboardDiv().appendChild(getPettingFarmOptions());
        getDashboardDiv().appendChild(getFieldOptions());
    }
}

