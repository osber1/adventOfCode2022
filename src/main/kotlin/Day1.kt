import util.loadInput

private val caloriesSums: MutableList<Int> = mutableListOf()
private var caloriesSum = 0

fun main() {
    loadInput(1)
        .map { parseToInt(it) }
        .forEach { addSumToList(it) }

    println(caloriesSums.max())
    println(caloriesSums.sorted().takeLast(3).sum())
}

private fun addSumToList(it: Int) {
    if (it == 0) {
        caloriesSums.add(caloriesSum)
        caloriesSum = 0
    } else
        caloriesSum += it
}

private fun parseToInt(it: String?): Int {
    if (it != "") {
        return Integer.parseInt(it)
    }
    return 0
}
