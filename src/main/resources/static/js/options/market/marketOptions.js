const marketOptions = getMarketOptions();

function getMarketOptions() {
    let marketDiv = document.createElement("div");
    let buyAndSellSection = getBuyAndSellSection();

    let marketTag = document.createElement("p");
    marketTag.innerHTML = "MARKET";
    marketTag.classList.add('underline', 'my-2');

    marketDiv.appendChild(marketTag);
    marketDiv.appendChild(buyAndSellSection);
    marketDiv.style.display = "none";

    return marketDiv;
}

function getBuyAndSellSection() {
    let buyAndSellDiv = document.createElement("div");
    buyAndSellDiv.appendChild(getBuyAndSellOption("cow", "", 120, 150));
    buyAndSellDiv.appendChild(getBuyAndSellOption("sheep", "", 80, 100));
    buyAndSellDiv.appendChild(getBuyAndSellOption("chicken", "", 30, 40));
    buyAndSellDiv.appendChild(getBuyAndSellOption("straw", "", 3, 5));
    buyAndSellDiv.appendChild(getBuyAndSellOption("corn", "", 2, 3));
    buyAndSellDiv.appendChild(getBuyAndSellOption("grass seed", "buy", 0, 3));
    buyAndSellDiv.appendChild(getBuyAndSellOption("corn seed", "buy", 0, 5));
    buyAndSellDiv.appendChild(getBuyAndSellOption("wheat seed", "buy", 0, 2));
    buyAndSellDiv.appendChild(getBuyAndSellOption("beef", "sell", 10, 0));
    buyAndSellDiv.appendChild(getBuyAndSellOption("wool", "sell", 20, 0));
    buyAndSellDiv.appendChild(getBuyAndSellOption("egg", "sell", 5, 0));
    buyAndSellDiv.appendChild(getBuyAndSellOption("lamb", "sell", 15, 0));
    buyAndSellDiv.appendChild(getBuyAndSellOption("milk", "sell", 8 , 0));
    buyAndSellDiv.appendChild(getBuyAndSellOption("bread", "sell", 5 , 0));
    buyAndSellDiv.appendChild(getBuyAndSellOption("field", "buy", 0, 400));
    return buyAndSellDiv;
}

function getBuyAndSellOption(item, option, sellPrice, buyPrice) {
    let buyAndSellOptionDiv = document.createElement("div");

    let sellButton = document.createElement("button");
        sellButton.innerHTML = "-";
        sellButton.addEventListener("click", ()=> {
        let marketRequestData = {"farmName": getFarmName(), "item":item, "units":1};
        sendMarketRequestSell(marketRequestData);
    })
    sellButton.setAttribute("data-toggle","tooltip");
    sellButton.setAttribute("data-placement", "bottom");
    sellButton.setAttribute("title", "Sell price £"+sellPrice);

    buyAndSellOptionDiv.appendChild(sellButton);

    let itemTag = document.createElement("p");
    itemTag.innerHTML = item.toUpperCase();
    itemTag.style.display = "inline-block";
    itemTag.classList.add("mx-2");
    buyAndSellOptionDiv.appendChild(itemTag);

    let buyButton = document.createElement("button");
        buyButton.innerHTML = "+";
        buyButton.addEventListener("click", ()=> {
        let marketRequestData = {"farmName": getFarmName(), "item":item, "units":1};
        sendMarketRequestBuy(marketRequestData);
    })

    buyButton.setAttribute("data-toggle","tooltip");
    buyButton.setAttribute("data-placement", "bottom");
    buyButton.setAttribute("title", "Buy price £"+buyPrice);

    buyAndSellOptionDiv.appendChild(buyButton);

    if (option === "sell") {
        buyButton.disabled = true;
    }

    if (option === "buy") {
        sellButton.disabled = true;
    }

    buyAndSellOptionDiv.classList.add('my-3');

    return buyAndSellOptionDiv;
}

function getMarketOptions1() {
    return marketOptions;
}