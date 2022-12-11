import util.loadInput

fun main() {
    val day10 = Day10()
    val input = loadInput(10).filter { it.isNotEmpty() }
    println(day10.first(input))
    day10.second(input)
}

class Day10 {
    private var signalStrength = 0
    private var cycle = 1

    fun first(input: List<String>): Int {
        var xValue = 1
        input.forEach { line ->
            if (line == "noop") processCycle(xValue)
            else {
                processCycle(xValue)
                processCycle(xValue)
                xValue += line.substringAfter(" ").toInt()
            }
        }
        return signalStrength
    }

    fun second(input: List<String>) {
        var xValue = 1
        signalStrength = 0
        cycle = 0
        input.forEach { line ->
            if (line == "noop") processCycleAndPrint(xValue)
            else {
                processCycleAndPrint(xValue)
                processCycleAndPrint(xValue)
                xValue += line.substringAfter(" ").toInt()
            }
        }
    }

    private fun processCycle(x: Int) {
        if (cycle % 40 == 20) signalStrength += cycle * x
        cycle++
    }

    private fun processCycleAndPrint(x: Int) {
        val positionInLine = cycle % 40
        if (positionInLine == 0) println()
        if (positionInLine in x - 1..x + 1) print("#")
        else print(".")
        cycle++
    }

}