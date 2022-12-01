import util.loadInput

private val list: MutableList<Int> = mutableListOf()
var sum = 0

fun main() {
    loadInput(1)
        .map { parseToInt(it) }
        .forEach { addSumToList(it) }

    println(list.max())
    println(list.sortedDescending().take(3).sum())
}

private fun addSumToList(it: Int) {
    if (it == 0) {
        list.add(sum)
        sum = 0
    } else
        sum += it
}

private fun parseToInt(it: String?): Int {
    if (it != "") {
        return Integer.parseInt(it)
    }
    return 0
}
