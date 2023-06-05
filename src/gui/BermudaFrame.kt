package gui

import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JPanel

object BermudaFrame : JFrame() {
    private val mainPanel: JPanel = JPanel()
    private val topPanel = JPanel()
    private val bottomPanel = JPanel()

    init {
        with(BermudaFrame) {
            setSize(900, 700)
            title = "Bermudaspiel"
            isResizable = true
            defaultCloseOperation = EXIT_ON_CLOSE
            setLocationRelativeTo(null)
            add(mainPanel)
        }

        with(mainPanel) {
            layout = BoxLayout(mainPanel, BoxLayout.Y_AXIS)
            add(topPanel)
            add(bottomPanel)

        }

        with(topPanel) {
            layout = BoxLayout(topPanel, BoxLayout.X_AXIS)
            add(TimerPanel)
            add(ResetButton)
        }

        with(bottomPanel) {
            layout = BoxLayout(bottomPanel, BoxLayout.Y_AXIS)
            add(BermudaPanel)
        }

        isVisible = true
    }
}