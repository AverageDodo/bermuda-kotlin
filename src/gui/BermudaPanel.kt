package gui

import java.awt.Dimension
import java.awt.GridLayout
import java.awt.Point
import javax.swing.JPanel

object BermudaPanel : JPanel() {
    var globalClickCounter = 0
    var spielfelders: Array<Array<Spielfelder?>>

    init {
        preferredSize = Dimension(900, 700)
        layout = GridLayout(9, 7, 1, 1)
        spielfelders = Array(9) { arrayOfNulls(7) }
        for (i in 0..8) {
            for (j in 0..6) {
                spielfelders[i][j] = Spielfelder(i, j)
                this.add(spielfelders[i][j])
            }
        }
    }

    fun getPanel(p: Point): Spielfelder? {
        return spielfelders[p.x][p.y]
    }
}