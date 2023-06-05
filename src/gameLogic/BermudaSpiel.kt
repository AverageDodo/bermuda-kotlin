package gameLogic

import gui.BermudaPanel
import java.awt.Point

object BermudaSpiel {
    @JvmField
    var flotte = Flotte(4)
    val checkedPanels = ArrayList<Point>()

    fun search(point: Point) {
        if (flotte.istHierSchiff(point)) {
            BermudaPanel.getPanel(point)?.setLabel("X")
        } else {
            checkedPanels.clear()
            var inSicht = 0
            inSicht += directionalSearch(point, -1, -1)
            inSicht += directionalSearch(point, -1, 0)
            inSicht += directionalSearch(point, -1, 1)
            inSicht += directionalSearch(point, 0, -1)
            inSicht += directionalSearch(point, 0, 1)
            inSicht += directionalSearch(point, 1, -1)
            inSicht += directionalSearch(point, 1, 0)
            inSicht += directionalSearch(point, 1, 1)
            BermudaPanel.getPanel(point)?.setLabel(inSicht.toString())
            if (inSicht == 0) {
                BermudaPanel.getPanel(point)?.givePanels(checkedPanels)
            }
        }
    }

    private fun directionalSearch(p: Point, dx: Int, dy: Int): Int {
        var anzahlGefunden = 0
        val b = Point(p.x + dx, p.y + dy)
        while (b.x in 0 .. 8 && b.y in 0 .. 6) {
            if (flotte.istHierSchiff(b)) {
                anzahlGefunden += 1
            }
            checkedPanels.add(Point(b))
            b.translate(dx, dy)
        }
        return anzahlGefunden
    }
}

