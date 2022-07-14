package nix.summer.practice.mvc

enum class Drink(val water: Int, val milk: Int, val coffeeBeans: Int, val cost: Int) {
    ESPRESSO(250, 0, 15, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);
}

data class Resources(var water: Int,
                     var milk: Int,
                     var coffeeBeans: Int,
                     var disposableCups: Int)

data class Response (var responseMessage: String)

data class Request(var command: String)