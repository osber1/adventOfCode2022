import util.loadInput

fun main() {
    val day4 = Day4()
    val input = loadInput(4).filter { it.isNotEmpty() }
    println(day4.first(input))
    println(day4.second(input))
}

class Day4 {
    fun first(input: List<String>): Int {
        return input
            .count {
                val pairs = it.split(",")
                val firstElf = buildList(pairs[0])
                val secondElf = buildList(pairs[1])

                val commonElements = firstElf.intersect(secondElf.toSet())

                commonElements.containsAll(firstElf) || commonElements.containsAll(secondElf)
            }
    }

    fun second(input: List<String>): Int {
        return input
            .count {
                val pairs = it.split(",")
                val firstElf = buildList(pairs[0])
                val secondElf = buildList(pairs[1])

                firstElf.intersect(secondElf.toSet()).isNotEmpty()
            }
    }

    private fun buildList(input: String): List<Int> {
        val range = input.split("-")
        return IntRange(range[0].toInt(), range[1].toInt()).toList()
    }

}