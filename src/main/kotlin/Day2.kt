import util.loadInput

private const val WIN = 6
private const val DRAW = 3
private const val LOSE = 0

private const val ROCK_POINTS = 1
private const val PAPER_POINTS = 2
private const val SCISSORS_POINTS = 3


fun main() {
    println(first())
    println(second())
}

fun first(): Int {
    return loadInput(2)
        .filter { it.isNotEmpty() }
        .sumOf { line ->
            if (line.endsWith('X')) {
                return@sumOf calculatePoints(line, DRAW, LOSE, WIN, ROCK_POINTS)
            } else if (line.endsWith('Y')) {
                return@sumOf calculatePoints(line, WIN, DRAW, LOSE, PAPER_POINTS)
            } else {
                return@sumOf calculatePoints(line, LOSE, WIN, DRAW, SCISSORS_POINTS)
            }
        }
}

fun second(): Int {
    return loadInput(2)
        .filter { it.isNotEmpty() }
        .sumOf { line ->
            if (line.endsWith('X')) {
                return@sumOf calculatePoints(line, SCISSORS_POINTS, ROCK_POINTS, PAPER_POINTS, 0)
            } else if (line.endsWith('Y')) {
                return@sumOf calculatePoints(line, ROCK_POINTS, PAPER_POINTS, SCISSORS_POINTS, DRAW)
            } else {
                return@sumOf calculatePoints(line, PAPER_POINTS, SCISSORS_POINTS, ROCK_POINTS, WIN)
            }
        }
}

private fun calculatePoints(line: String, rock: Int, paper: Int, scissors: Int, extraPoints: Int): Int {
    return extraPoints + when (line[0]) {
        'A' -> rock
        'B' -> paper
        else -> scissors
    }
}

