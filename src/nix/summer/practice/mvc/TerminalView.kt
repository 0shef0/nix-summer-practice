package nix.summer.practice.mvc

import kotlin.system.exitProcess

class TerminalView(override var controller: Controller): View {

    override fun buyInput() {
        while (true) {
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
            try {
                val userChoice: String = readln()
                if (userChoice == "back") {
                    controller.buyChoice(0)
                    break
                }
                val typeOfCup = userChoice.toInt()
                controller.buyChoice(typeOfCup)
                break
            } catch (e: NumberFormatException) {
                println("Please, enter only number of variant or type back")
            }
        }
    }

    private fun checkInt(ingredient: String): Int{
        println("Write how many $ingredient you want to add:")
        var res: Int
        while (true) {
            try {
                res = readln().toInt()
                if (res < 0) {
                    throw Exception()
                }
                break
            } catch (e: NumberFormatException) {
                println("Please, enter only positive numbers or zero")
            }
        }
        return res

    }

    private fun fillInput(): Resources {
        return Resources(checkInt("ml of water"),
                        checkInt("ml of milk"),
                        checkInt("grams of coffee beans"),
                        checkInt("disposable cups"))
    }

    override fun showInfo(response: Response) {
        println(response.responseMessage)
    }

    override fun start() {
        while (true){
            println("write command: buy, take, fill, remaining, exit")
            val request = Request(readln())
            if(request.command == "fill") {
                controller.takeCommand(request, fillInput())
            } else {
                controller.takeCommand(request)
            }
        }
    }

    override fun exit() {
        println("goodbye, have a nice day")
        exitProcess(0)
    }
}