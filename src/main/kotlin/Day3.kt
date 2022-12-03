import util.loadInput

fun main() {
    val day3 = Day3()
    val input = loadInput(3).filter { it.isNotEmpty() }
    println(day3.first(input))
    println(day3.second(input))
}

class Day3 {
    fun first(input: List<String>): Int {
        return input
            .map { buildPair(it) }
            .sumOf { pair -> pair.first.first { it in pair.second }.priorityValue() }
    }

    fun second(input: List<String>): Int {
        return input
            .chunked(3)
//            .sumOf { (it[0].toSet() intersect it[1].toSet() intersect it[2].toSet()).single().priorityValue() }
            .sumOf { item -> item[0].first { it in item[1] && item[2].contains(it) }.priorityValue() }
    }

    private fun buildPair(string: String): Pair<String, String> {
        val middle = string.length / 2
        return Pair(string.substring(0, middle), string.substring(middle))
    }

    private fun Char.priorityValue(): Int {
        return when (isLowerCase()) {
            true -> code - 96
            else -> code - 38
        }
    }
}