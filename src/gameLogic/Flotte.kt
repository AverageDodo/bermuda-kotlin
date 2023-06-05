package gameLogic

import java.awt.Point
import java.util.*

class Flotte(anzahl: Int) : Schiff() {
    private val flotte: Vector<Schiff> = Vector()

    init {
        var i = 0
        while (i < anzahl) {
            flotte.add(Schiff())
            for (j in 0 .. i) {
                if (i != 0 && flotte.elementAt(i) == flotte.elementAt(j)) {
                    i -= 1
                }
            }
            i++
        }
    }

    fun gewonnen(): Boolean {
        for (s in flotte) {
            if (!s.istGefunden()) {
                return false
            }
        }
        return true
    }

    fun istHierSchiff(suchPunkt: Point): Boolean {
        for (schiff in flotte) {
            if (schiff.p == suchPunkt) {
                return true
            }
        }
        return false
    }

    fun setSchiffGefunden(point: Point) {
        for (s in flotte) {
            if (s.p == point) {
                s.setGefunden()
                return
            }
        }
    }
}