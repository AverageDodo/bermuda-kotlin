package gui

import gameLogic.BermudaSpiel
import gameLogic.Flotte
import javax.swing.JButton

object ResetButton : JButton() {

	init {
		text = "Reset Button"
		isVisible = false

		addActionListener {
			with(TimerPanel) {
				timer.restart()
				timer.stop()
				seconds = 0
			}

			with(BermudaSpiel) {
				flotte = Flotte(4)
				checkedPanels.clear()
			}

			with(BermudaPanel) {
				globalClickCounter = 0
				for (i in 0..8) {
					for (j in 0..6) {
						val feld = spielfelders[i][j]
						feld?.panelPosition?.let { Spielfelder.hidePanel(it) }
						feld?.revealOnClick?.clear()
						feld?.clickCounter = 0

						feld?.panelPosition?.let { BermudaSpiel.search(it) }
					}
				}
			}

		}
	}
}