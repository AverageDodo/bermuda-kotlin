import gameLogic.BermudaSpiel
import gameLogic.Scores
import java.awt.Point

object Main {
    @JvmStatic
    fun main(args: Array<String>) {

        val test = Point()
        for (i in 0..8) {
            for (j in 0..6) {
                test.setLocation(i, j)
                BermudaSpiel.search(test)
            }
        }

        Runtime.getRuntime().addShutdownHook(Thread {
            Scores.saveScoresToFile()
        })
    }
}
