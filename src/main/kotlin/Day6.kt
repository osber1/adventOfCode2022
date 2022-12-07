import util.loadInput

fun main() {
    val day6 = Day6()
    val input = loadInput(6).filter { it.isNotEmpty() }
    println(day6.first(input))
    println(day6.second(input))
}

class Day6 {

    fun first(input: List<String>): Int {
        return findUniqueCodeIndex(input, 4)

    }

    fun second(input: List<String>): Int {
        return findUniqueCodeIndex(input, 14)
    }

    private fun findUniqueCodeIndex(input: List<String>, markerSize: Int): Int {
        return input.first()
            .windowed(markerSize)
            .indexOfFirst { it.toSet().size == markerSize }
            .plus(markerSize)
    }

}