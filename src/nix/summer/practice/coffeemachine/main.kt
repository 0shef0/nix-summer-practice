package nix.summer.practice.coffeemachine

enum class Drink(val water: Int, val milk: Int, val coffeeBeans: Int, val cost: Int) {
    ESPRESSO(250, 0, 15, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);
}

class CoffeeMachine () {
    private var water = 400
    private var milk = 540
    private var coffeeBeans = 120
    private var disposableCups = 9
    private var money = 550

    fun menu(action: String) {
        when (action) {
            "buy" -> {
                while (true) {
                    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                    val check: Boolean = buyCheck()
                    if (check) {
                        break
                    }
                }
            }
            "fill" -> {
                println("Write how many ml of water you want to add:")
                val fillWater = checkForInt()
                println("Write how many ml of milk you want to add:")
                val fillMilk = checkForInt()
                println("Write how many grams of coffee beans you want to add:")
                val fillCoffeeBeans = checkForInt()
                println("Write how many disposable coffee cups you want to add:")
                val fillDisposableCups = checkForInt()
                this.fill(fillWater, fillMilk, fillCoffeeBeans, fillDisposableCups)
            }
            "take" -> {
                this.take()
            }
            "remaining" -> {
                this.coffeeMachineHas()
            }
            else -> {
                println("there is no such command")
            }
        }
    }

    fun coffeeMachineHas () {
        println("The coffee machine has:\n" +
                "${this.water} ml of water\n" +
                "${this.milk} ml of milk\n" +
                "${this.coffeeBeans} g of coffee beans\n" +
                "${this.disposableCups} of dispasable cups\n" +
                "${this.money} of money")
    }

    fun buyCheck(): Boolean {
            try {
                val userChoise: String = readln()
                if (userChoise == "back") {
                    return true
                }
                val typeOfCup = userChoise.toInt()
                when (typeOfCup) {
                    1 -> {
                        this.buy(Drink.ESPRESSO)
                    }
                    2 -> {
                        this.buy(Drink.LATTE)
                    }
                    3 -> {
                        this.buy(Drink.CAPPUCCINO)
                    }
                    else -> {
                        println("there is no such drink")
                    }
                }
                return true
            } catch (e: NumberFormatException) {
                println("Please, enter only number of variant or type back")
                return false
            }
    }
    fun buy(drink: Drink) {
        if (drink.water > this.water) {
            println("Sorry, not enough water")
            return
        }
        else if (drink.milk > this.milk) {
            println("Sorry, not enough milk")
            return
        }
        else if (drink.coffeeBeans > this.coffeeBeans) {
            println("Sorry, not enough coffee beans")
            return
        }
        else if (this.disposableCups < 1) {
            println("Sorry, not enough disposable cups")
            return
        } else {
            println("I have enough resources, making you a coffee!")
        }

       this.water -= drink.water
       this.milk -= drink.milk
       this.coffeeBeans -= drink.coffeeBeans
       this.disposableCups -= 1
       this.money += drink.cost
    }

    fun fill(water: Int, milk: Int, coffeeBeans: Int, disposableCups: Int){
        this.water += water
        this.milk += milk
        this.coffeeBeans += coffeeBeans
        this.disposableCups += disposableCups
    }

    fun take() {
        println("I gave you ${this.money}")
        this.money = 0
    }
}

fun checkForInt(): Int{
    var arg: Int
    while (true) {
        try {
            arg = readln().toInt()
            if (arg < 0) {
                throw Exception()
            }
            break
        } catch (e: Exception) {
            println("Please, enter only positive numbers or zero")
        }
    }
    return arg
}

fun main (args: Array<String>) {

    val machine = CoffeeMachine()

    while (true) {
        println("Write action (buy, fill, take, remaining, exit)")
        val action = readln()
        if(action == "exit") {
            break
        }
        machine.menu(action)
    }
}