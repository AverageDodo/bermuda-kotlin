package gui

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.Timer

object TimerPanel : JPanel() {
    var seconds = 0
    private val timerLabel: JLabel
    var timer: Timer

    init {
        preferredSize = Dimension(900, 100)
        timerLabel = JLabel()
        timerLabel.size = this.preferredSize
        timerLabel.font = Font("Arial", Font.BOLD, 50)
        timerLabel.horizontalAlignment = JLabel.CENTER
        timerLabel.verticalAlignment = JLabel.CENTER
        add(timerLabel, BorderLayout.CENTER)
        val actionListener = ActionListener { e: ActionEvent? ->
            seconds++
            timerLabel.text = (seconds / 10).toString() + (seconds % 10).toString()
        }
        timer = Timer(1000, actionListener)
    }
}
