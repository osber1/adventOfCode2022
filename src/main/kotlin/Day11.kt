import util.loadInput

fun main() {
    val input = loadInput(11).filter { it.isNotEmpty() }
    val day11 = Day11(input)
    println(day11.first())
    println(day11.second())
}

class Day11(val input: List<String>) {
    fun first(): Int {
        val monkeys = buildMonkeysList(input)
        repeat(20) {
            monkeys.values.forEach { monkey ->
                monkey.items.forEach { item ->
                    monkey.timesInspected++
                    val newWorryLevel = monkey.operation(item) / 3
                    when (newWorryLevel % monkey.divisibleBy == 0L) {
                        true -> monkeys[monkey.ifTrue]!!.items += newWorryLevel
                        false -> monkeys[monkey.ifFalse]!!.items += newWorryLevel
                    }
                    monkey.items -= item
                }
            }
        }
        return monkeys.values.map { it.timesInspected }.sorted().takeLast(2).reduce { a, b -> a * b }
    }

    fun second(): Long {
        val monkeys = buildMonkeysList(input)
        val mod = monkeys.values.map { it.divisibleBy }.reduce { a, b -> a * b }
        repeat(10000) {
            monkeys.values.forEach { monkey ->
                monkey.items.forEach { item ->
                    monkey.timesInspected++
                    val newWorryLevel = monkey.operation(item) % mod
                    when (newWorryLevel % monkey.divisibleBy == 0L) {
                        true -> monkeys[monkey.ifTrue]!!.items += newWorryLevel
                        false -> monkeys[monkey.ifFalse]!!.items += newWorryLevel
                    }
                    monkey.items -= item
                }
            }
        }
        return monkeys.values.map { it.timesInspected }.sorted().takeLast(2).map { it.toLong() }.reduce { a, b -> a * b }
    }

    private fun buildMonkeysList(input: List<String>): MutableMap<String, Monkey> {
        val monkeys = mutableMapOf<String, Monkey>()
        input.chunked(6).forEach { monkey ->
            val name = monkey[0].dropLast(1).lowercase()
            val items = monkey[1].substringAfter("items: ").split(", ").map { it.toLong() }
            val divisibleBy = monkey[3].substringAfter("by ").toInt()
            val ifTrue = monkey[4].substringAfter("to ")
            val ifFalse = monkey[5].substringAfter("to ")
            val function = when {
                monkey[2].contains("+") -> buildPlusFunction(monkey[2])
                else -> buildMultiplyFunction(monkey[2])
            }
            monkeys[name] = Monkey(name, items, function, divisibleBy, ifTrue, ifFalse, 0)
        }
        return monkeys
    }

    private fun buildMultiplyFunction(operation: String): (Long) -> Long {
        val number = operation.substringAfter("* ")
        if (number.toCharArray()[0].isDigit()) {
            return { a: Long -> a * number.toInt() }
        }
        return { a: Long -> a * a }
    }

    private fun buildPlusFunction(operation: String): (Long) -> Long = { a: Long -> a + operation.substringAfter("+ ").toInt() }

    data class Monkey(
        val name: String,
        var items: List<Long>,
        val operation: (Long) -> Long,
        val divisibleBy: Int,
        val ifTrue: String,
        val ifFalse: String,
        var timesInspected: Int
    )
}