package nix.summer.practice.mvc

class Model {

    private var water: Int = 400
    private var milk: Int = 540
    private var coffeeBeans: Int = 120
    private var disposableCups: Int = 9
    private var money: Int = 550

    fun info(): Response {
        return Response("water: ${this.water} ml;\n" +
                "milk: ${this.milk} ml;\n" +
                "coffee beans: ${this.coffeeBeans} g;\n" +
                "disposable cups: ${this.disposableCups};\n" +
                "money: ${this.money}\n")
    }

    fun buy(drink: Drink): Response {
        if (drink.water > this.water) {
            return Response("Sorry, not enough water")
        }
        else if (drink.milk > this.milk) {
            return Response("Sorry, not enough milk")
        }
        else if (drink.coffeeBeans > this.coffeeBeans) {
            return Response("Sorry, not enough coffee beans")
        }
        else if (this.disposableCups < 1) {
            return Response("Sorry, not enough disposable cups")
        }

        this.water -= drink.water
        this.milk -= drink.milk
        this.coffeeBeans -= drink.coffeeBeans
        this.disposableCups -= 1
        this.money += drink.cost
        return Response("I have enough resources, making you a coffee!")
    }

    fun take(): Response {
        val info = Response("I've gave you ${this.money}")
        this.money = 0
        return info
    }

    fun fill(resources: Resources): Response {
        this.water += resources.water
        this.milk += resources.milk
        this.coffeeBeans += resources.coffeeBeans
        this.disposableCups += resources.disposableCups
        return Response("Filled")
    }
}