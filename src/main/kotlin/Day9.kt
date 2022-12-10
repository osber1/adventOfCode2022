import util.loadInput

fun main() {
    val input = loadInput(9).filter { it.isNotEmpty() }
    val day9 = Day9(input)
    println(day9.first())
    println(day9.second())
}

class Day9(input: List<String>) {
    private val commands = input.map { it.split(" ") }.map { (direction, steps) -> Movement(direction.toCharArray().first(), steps.toInt()) }.toList()

    fun first(): Int {
        val visited = mutableSetOf<Point>()
        var head = Point(0, 0)
        var tail = Point(0, 0)
        commands.forEach {
            for (step in 1..it.steps) {
                head = moveHead(it, head)
                tail = moveTail(tail, head)
                visited += tail
            }
        }
        return visited.count()
    }

    fun second(): Int {
        val visited = mutableSetOf<Point>()
        var head = Point(0, 0)
        val knots = MutableList(9) { Point(0, 0) }
        commands.forEach {
            for (step in 1..it.steps) {
                head = moveHead(it, head)
                for (knot in 0 until knots.size) {
                    val tail = when (knot) {
                        0 -> moveTail(knots[knot], head)
                        else -> moveTail(knots[knot], knots[knot - 1])
                    }
                    knots[knot] = tail
                    if (knot == 8) {
                        visited += tail
                    }
                }
            }
        }
        return visited.count()
    }

    private fun moveTail(tail: Point, head: Point): Point {
        return when {
            tail.x + 2 == head.x && tail.y == head.y -> Point(tail.x + 1, tail.y)
            tail.x - 2 == head.x && tail.y == head.y -> Point(tail.x - 1, tail.y)
            tail.x == head.x && tail.y + 2 == head.y -> Point(tail.x, tail.y + 1)
            tail.x == head.x && tail.y - 2 == head.y -> Point(tail.x, tail.y - 1)
            tail.x + 2 == head.x && tail.y + 2 == head.y -> Point(tail.x + 1, tail.y + 1)
            tail.x + 1 == head.x && tail.y + 2 == head.y -> Point(tail.x + 1, tail.y + 1)
            tail.x + 2 == head.x && tail.y + 1 == head.y -> Point(tail.x + 1, tail.y + 1)
            tail.x - 2 == head.x && tail.y - 2 == head.y -> Point(tail.x - 1, tail.y - 1)
            tail.x - 1 == head.x && tail.y - 2 == head.y -> Point(tail.x - 1, tail.y - 1)
            tail.x - 2 == head.x && tail.y - 1 == head.y -> Point(tail.x - 1, tail.y - 1)
            tail.x + 2 == head.x && tail.y - 2 == head.y -> Point(tail.x + 1, tail.y - 1)
            tail.x + 1 == head.x && tail.y - 2 == head.y -> Point(tail.x + 1, tail.y - 1)
            tail.x + 2 == head.x && tail.y - 1 == head.y -> Point(tail.x + 1, tail.y - 1)
            tail.x - 2 == head.x && tail.y + 2 == head.y -> Point(tail.x - 1, tail.y + 1)
            tail.x - 1 == head.x && tail.y + 2 == head.y -> Point(tail.x - 1, tail.y + 1)
            tail.x - 2 == head.x && tail.y + 1 == head.y -> Point(tail.x - 1, tail.y + 1)
            else -> tail
        }
    }

    private fun moveHead(movement: Movement, head: Point): Point {
        return when (movement.direction) {
            'U' -> Point(head.x, head.y + 1)
            'D' -> Point(head.x, head.y - 1)
            'L' -> Point(head.x - 1, head.y)
            'R' -> Point(head.x + 1, head.y)
            else -> head
        }
    }

    data class Movement(val direction: Char, val steps: Int)
    data class Point(val x: Int, val y: Int)
}