import util.loadInput

fun main() {
    val day6 = Day6()
    val input = loadInput(6).filter { it.isNotEmpty() }
    println(day6.first(input))
    println(day6.second(input))
}

class Day6 {

    fun first(input: List<String>): Int {
        return findUniqueCode(input, 4)

    }

    fun second(input: List<String>): Int {
        return findUniqueCode(input, 14)
    }

    private fun findUniqueCode(input: List<String>, markerSize: Int): Int {
        val dataStream = input[0]
        val uniqueCode = dataStream
            .toCharArray().toList()
            .windowed(markerSize)
            .first { it.toSet().size == markerSize }
            .joinToString("")

        return dataStream.indexOf(uniqueCode) + markerSize
    }

}