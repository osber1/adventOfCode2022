import util.loadInput

fun main() {
    val day5 = Day5()
    val input = loadInput(5).filter { it.isNotEmpty() }
    println(day5.first(input))
    println(day5.second(input))
}

class Day5 {
    fun first(input: List<String>): String {
        val stacks = buildMap(input)
        input.filter { "move" in it }.forEach { line ->
            val (amount, from, to) = "\\d+".toRegex().findAll(line).map { it.value.toInt() }.toList()
            repeat(amount) {
                stacks[to] = stacks.getValue(to) + stacks.getValue(from).last()
                stacks[from] = stacks.getValue(from).dropLast(1)
            }
        }
        return stacks.values.map { it.last() }.joinToString("")
    }

    fun second(input: List<String>): String {
        val stacks = buildMap(input)
        input.filter { "move" in it }
            .forEach { line ->
                val (amount, from, to) = "\\d+".toRegex().findAll(line).map { it.value.toInt() }.toList()
                stacks[to] = stacks.getValue(to) + stacks.getValue(from).takeLast(amount)
                stacks[from] = stacks.getValue(from).dropLast(amount)
            }
        return stacks.values.map { it.last() }.joinToString("")
    }

    private fun buildMap(input: List<String>): MutableMap<Int, List<Char>> {
        val stacks = (1..9).associateWith { emptyList<Char>() }.toMutableMap()
        input.filter { '[' in it }
            .forEach { line ->
                for (stackIndex in 1..9) {
                    val lineIndex = 1 + (stackIndex - 1) * 4
                    if (line.lastIndex < lineIndex || line[lineIndex] == ' ') continue
                    stacks[stackIndex] = listOf(line[lineIndex]) + stacks.getValue(stackIndex)
                }
            }
        return stacks
    }

}