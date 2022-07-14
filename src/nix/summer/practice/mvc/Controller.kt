package nix.summer.practice.mvc

class Controller(private val model: Model) {

    private lateinit var view: View

    fun buyChoice(command: Int) {
        when(command) {
            1 -> {
                view.showInfo(model.buy(Drink.ESPRESSO))
            }
            2 -> {
                view.showInfo(model.buy(Drink.LATTE))
            }
            3 -> {
                view.showInfo(model.buy(Drink.CAPPUCCINO))
            }
            0 -> {
                view.showInfo(Response("Returned"))
            }
            else -> {
                view.showInfo(Response("There is no such a drink"))
            }
        }
    }

    fun attachView(_view: View) {
        view = _view
    }

    fun start() {
        view.start()
    }

    fun takeCommand(request: Request, resources: Resources = Resources(0,0,0,0)) {
        when (request.command) {
            "buy" -> {
                view.buyInput()
            }
            "take" -> {
                view.showInfo(model.take())
            }
            "fill" -> {
                view.showInfo(model.fill(resources))
            }
            "remaining" -> {
                view.showInfo(model.info())
            }
            "exit" -> {
                view.exit()
            }
            else -> view.showInfo(Response("choose only available commands"))
        }
    }
}