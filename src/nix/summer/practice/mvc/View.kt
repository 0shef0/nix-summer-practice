package nix.summer.practice.mvc

interface View {

    var controller: Controller

    fun buyInput()

    fun showInfo(response: Response)

    fun start()

    fun exit()
}