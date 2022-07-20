package nix.summer.practice.mvc

fun main(args: Array<String>) {

    val model = Model()
    val controller = Controller(model)
    println(
        "where do you want to start program:\n" +
                "1-terminal\n" +
                "2-window\n"
    )
    var viewType: Int;
    while (true) {
        try {
            viewType = readln().toInt()
            if (viewType < 1 || viewType > 2) {
                throw Exception()
            }
            break
        } catch (e: Exception) {
            println("enter number of your variant")
        }
    }
    val view: View
    while (true) {
        when (viewType) {
            1 -> {
                view = TerminalView(controller)
                break
            }
            2 -> {
                view = SwingView(controller)
                break
            }
            else -> {
                println("choose one of two variants")
            }
        }
    }
    controller.attachView(view)
    controller.start()

}