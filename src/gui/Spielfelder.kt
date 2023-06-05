package gui

import gameLogic.BermudaSpiel.flotte
import gameLogic.Scores
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.*
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.border.LineBorder

class Spielfelder(x: Int, y: Int) : JPanel() {

    companion object {
        fun revealPanel(p: Point) {
            BermudaPanel.getPanel(p)?.background = Color.red
            BermudaPanel.getPanel(p)?.label?.isVisible = true
        }

        fun hidePanel(p: Point) {
            BermudaPanel.getPanel(p)?.background = Color.white
            BermudaPanel.getPanel(p)?.label?.isVisible = false
        }

        fun selectPanel(p: Point) {
            BermudaPanel.getPanel(p)?.background = Color.red
        }

        fun deselectPanel(p: Point) {
            BermudaPanel.getPanel(p)?.background = Color.white
        }
    }

    val label: JLabel = JLabel()
    var panelPosition: Point = Point()
    var revealOnClick = ArrayList<Point>()
    var clickCounter = 0

    init {
        panelPosition.setLocation(x, y)
        revealOnClick.add(0, panelPosition)
        layout = BorderLayout()
        background = Color.white

        val mouseListener = object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                clickCounter++
                if (BermudaPanel.globalClickCounter == 0) {
                    TimerPanel.timer.start()
                    ResetButton.isVisible = true
                    BermudaPanel.globalClickCounter++
                }

                if (Objects.equals(label.text, "X")) {
                    flotte.setSchiffGefunden(panelPosition)
                }

                if (e.button == MouseEvent.BUTTON1) {
                    if (clickCounter / 2 != 0) {
                        for (p in revealOnClick) {
                            hidePanel(p)
                        }
                    } else {
                        for (p in revealOnClick) {
                            revealPanel(p)
                        }
                    }
                } else {
                    if (background == Color.red) {
                        deselectPanel(panelPosition)
                    } else {
                        selectPanel(panelPosition)
                    }
                }
                when {
                    flotte.gewonnen() -> {
                        TimerPanel.timer.stop()
                        JOptionPane.showMessageDialog(BermudaPanel, "Game finished!")

                        val name = JOptionPane.showInputDialog("Please enter name.")
                        Scores.addScore(TimerPanel.seconds, name)

                        JOptionPane.showMessageDialog(BermudaPanel, Scores.printScores())
                    }
                }
            }
        }
        addMouseListener(mouseListener)

        border = LineBorder(Color.BLACK, 2, true)
        isVisible = true
        label.horizontalAlignment = SwingConstants.CENTER
        add(label, BorderLayout.CENTER)
        label.isVisible = false
    }

    fun setLabel(str: String?) {
        label.text = str
    }

    fun givePanels(arrayList: ArrayList<Point>) {
            revealOnClick.addAll(arrayList)
    }
}
