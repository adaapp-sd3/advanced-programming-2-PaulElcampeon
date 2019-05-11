# advanced-programming-2-PaulElcampeon

# Farm-App

Beta can be accessed at https://farm-app-beta.herokuapp.com/

Graphics library used: Pixi.js

## Quick tip

* When playing on mobile devices make sure to click on the fullscreen button on the top of the dashboard.


## <p align="center">Game Rules</p>

### Constraints
* Cannot have more than 100 of the same type of animal on your farm.
* Cannot have more than 100 visitors on your farm.
* Cannot have more than 20 animals in one field.
* Animals can only live on grazing fields or petting farms.
* You can only have up to 5 fields.
* If a field has 20 animals living on it and you buy another animal, the animal will be added to the next grazing field or petting farm if available.
* You can only plant seeds on growing fields.

### Field Conversions
#### Conversion between fields
* If you convert a petting farm field to a grazing field whilst you have animals and tourist, this will get rid of all the tourist on the petting farm.
* If you convert a grazing field to a growing field whist animals are present on the field, you will lose all these animals.
* If you convert a growing field to a grazing field while crops are present, all the crops will be destroyed. 
* In order to convert between fields you muse move your tractor over the field, once there you will be given options in your dashboard.

### Market
* In order to buy produce and land, you must move your tractor on top of the market building, once there you will be shown options on your dashboard, to find the price of the items you must move your mouse over the + or - buttons.

## <p align="center">Key Info</p>

### Your animals will die if you do not feed them.
* Cows eat straw.
* Sheep eat straw.
* Chickens eat corn.

### Animal produce.
* Cows produce milk periodically and beef when reached full maturity.
* Sheep produce wool periodically and lamb when reached full maturity.
* Chickens produce eggs periodically.

### Planting seeds.
* Grass seed produces straw.
* Corn seed produces corn.
* Wheat seed produces bread.

### Petting farms profits.
* In order to make profit from a petting farm you must have at least 1 type of animal present in the petting farm for tourists to come and visit your farm. Tourist will pay an amount to visit your farm.
