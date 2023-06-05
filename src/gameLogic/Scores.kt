package gameLogic

import gui.BermudaPanel
import java.io.File
import javax.swing.JOptionPane

object Scores {

    private val scores: HashMap<Int, String> = HashMap()
    private val sortedScores = scores.entries.sortedBy { it.key }.associate { it.key to it.value }
    private val fileName: String = "${System.getProperty("user.dir")}/highscores.txt"

    init {
        loadScores()
    }

    fun addScore(score: Int, name: String) {
        scores.set(score, name)
    }

    fun saveScoresToFile() {
        File(fileName).writeText(sortedScores.entries.joinToString(System.lineSeparator()) {
            "${it.key}=${it.value}"
        })
    }

    private fun loadScores() {
        if (File(fileName).exists()) {
            File(fileName).readLines().forEach { entry ->
                scores.set(entry.substring(0, entry.indexOfFirst { it == '=' }).toInt(),
                    entry.substring(entry.indexOfFirst { it == '=' } + 1))
            }
        } else {
            JOptionPane.showMessageDialog(BermudaPanel, "Could not load scores.")
        }
    }

    fun printScores() : String {
        return sortedScores.entries.joinToString(System.lineSeparator(), "", "", 5) {
            "${it.key}=${it.value}"
        }
    }
}