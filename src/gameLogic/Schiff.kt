package gameLogic

import java.awt.Point
import java.util.*

open class Schiff {
    private var gefunden = false
    val p: Point

    init {
        val ran = Random()
        p = Point(ran.nextInt(9), ran.nextInt(7))
        gefunden = false
    }

    fun istGefunden(): Boolean {
        return gefunden
    }

    fun setGefunden() {
        gefunden = true
    }
}