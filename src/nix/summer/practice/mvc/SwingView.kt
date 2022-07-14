package nix.summer.practice.mvc

import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import kotlin.system.exitProcess

class SwingView(override var controller: Controller): JFrame(), View {

    private lateinit var mainFrame: JFrame
    private lateinit var controlPanel: JPanel
    private lateinit var messageLabel: JLabel
    private lateinit var infoLabel: JLabel

    private lateinit var buyFrame: JFrame
    private lateinit var buyPanel: JPanel

    private lateinit var fillFrame: JFrame
    private lateinit var fillPanel: JPanel
    private lateinit var numInputWater: JTextField
    private lateinit var numInputMilk: JTextField
    private lateinit var numInputCoffeeBeans: JTextField
    private lateinit var numInputDisposableCups: JTextField

    override fun start() {
        createUI()
        createBuyUI()
        createFillUI()
    }

    private fun createUI() {
        title = SwingView::class.java.toString()
        controlPanel = JPanel().apply { layout = FlowLayout() }

        mainFrame = JFrame("Main Menu").apply {
            setSize(600,200)
            layout = GridLayout(3,1)
            addWindowListener(object: WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    exit()
                }
            })
            add(controlPanel)
        }

        createControlPanel()


        mainFrame.isVisible = true
    }

    private fun createControlPanel() {
        messageLabel = JLabel("choose what do you want to do")
        controlPanel.add(messageLabel)

        val buyButton = JButton("buy").apply {
            actionCommand = "buy"
            addActionListener{
                mainFrame.isVisible = false
                buyFrame.isVisible = true
                controller.takeCommand(Request(actionCommand))
            }
        }

        val fillButton = JButton("fill").apply {
            addActionListener{
                mainFrame.isVisible = false
                fillFrame.isVisible = true
            }
        }

        val remainingButton = JButton("show resources").apply {
            actionCommand = "remaining"
            addActionListener{
                controller.takeCommand(Request(actionCommand))
            }
        }

        val takeButton = JButton("Take Money").apply {
            actionCommand = "take"
            addActionListener{
                controller.takeCommand(Request(actionCommand))
            }
        }
        infoLabel = JLabel("").apply { layout = FlowLayout() }

        controlPanel.add(buyButton)
        controlPanel.add(fillButton)
        controlPanel.add(remainingButton)
        controlPanel.add(takeButton)
        controlPanel.add(infoLabel)
    }



    private fun createBuyUI() {
        title = SwingView::class.java.toString()
        buyPanel = JPanel().apply { layout = FlowLayout() }

        buyFrame = JFrame("Buy Menu").apply {
            setSize(600, 200)
            layout = GridLayout(4,1)
            addWindowListener(object: WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    fillFrame.isVisible = false
                    mainFrame.isVisible = true
                }
            })
            add(buyPanel)
        }

        buyFrame.isVisible = false
    }

    private fun createFillUI() {
        title = SwingView::class.java.toString()
        fillPanel = JPanel().apply { layout = FlowLayout() }

        fillFrame = JFrame("fill menu").apply {
            setSize(600, 200)
            layout = GridLayout(4,1)
            addWindowListener(object: WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    fillFrame.isVisible = false
                    mainFrame.isVisible = true
                }
            })
            add(fillPanel)
        }

        createFillPanel()
        fillFrame.isVisible = false
    }

    private fun createFillPanel() {
        numInputWater = JTextField("0", 4)
        numInputMilk = JTextField("0", 4)
        numInputCoffeeBeans = JTextField("0", 4)
        numInputDisposableCups = JTextField("0", 4)

        fillPanel.add(numInputWater)
        fillPanel.add(numInputMilk)
        fillPanel.add(numInputCoffeeBeans)
        fillPanel.add(numInputDisposableCups)

        val fillButton = JButton("Fill").apply {
            addActionListener {
                val water = numInputWater.text.toInt()
                val milk = numInputMilk.text.toInt()
                val coffeeBeans = numInputCoffeeBeans.text.toInt()
                val disposableCups = numInputDisposableCups.text.toInt()
                controller.takeCommand(
                    Request("fill"),
                                        Resources(water, milk, coffeeBeans, disposableCups)
                )
                numInputWater.text = "0"
                numInputMilk.text = "0"
                numInputCoffeeBeans.text = "0"
                numInputDisposableCups.text = "0"

                fillFrame.isVisible = false
                mainFrame.isVisible = true
            }
        }

        fillPanel.add(fillButton)

    }

    private fun buyAction(command: Int) {
        when(command){
            0 -> controller.buyChoice(0)
            1 -> controller.buyChoice(1)
            2 -> controller.buyChoice(2)
            3 -> controller.buyChoice(3)
        }
        buyFrame.isVisible = false
        mainFrame.isVisible = true
        buyPanel.removeAll()
    }

    override fun buyInput() {
        buyFrame.revalidate()
        buyPanel.revalidate()
        val espresso = JButton("espresso").apply {
            addActionListener{
                buyAction(1)
            }
        }

        val latte = JButton("latte").apply {
            addActionListener{
                buyAction(2)
            }
        }

        val cappuccino = JButton("cappuccino").apply {
            addActionListener{
                buyAction(3)
            }
        }

        val back = JButton("back").apply {
            addActionListener{
                buyAction(0)
            }
        }

        buyPanel.add(espresso)
        buyPanel.add(latte)
        buyPanel.add(cappuccino)
        buyPanel.add(back)
    }


    override fun showInfo(response: Response) {
        infoLabel.text = response.responseMessage
        mainFrame.revalidate()
    }

    override fun exit() {
        exitProcess(0)
    }

}