import util.loadInput

private const val WIN = 6
private const val DRAW = 3
private const val LOSE = 0

private const val ROCK_POINTS = 1
private const val PAPER_POINTS = 2
private const val SCISSORS_POINTS = 3


fun main() {
    val day2 = Day2()
    val input = loadInput(2).filter { it.isNotEmpty() }
    println(day2.first(input))
    println(day2.second(input))
}

class Day2 {
    fun first(input: List<String>): Int {
        return input.sumOf { line ->
            when (line[2]) {
                'X' -> return@sumOf calculatePoints(line, DRAW, LOSE, WIN, ROCK_POINTS)
                'Y' -> return@sumOf calculatePoints(line, WIN, DRAW, LOSE, PAPER_POINTS)
                else -> return@sumOf calculatePoints(line, LOSE, WIN, DRAW, SCISSORS_POINTS)
            }
        }
    }

    fun second(input: List<String>): Int {
        return input.sumOf { line ->
            when (line[2]) {
                'X' -> return@sumOf calculatePoints(line, SCISSORS_POINTS, ROCK_POINTS, PAPER_POINTS, 0)
                'Y' -> return@sumOf calculatePoints(line, ROCK_POINTS, PAPER_POINTS, SCISSORS_POINTS, DRAW)
                else -> return@sumOf calculatePoints(line, PAPER_POINTS, SCISSORS_POINTS, ROCK_POINTS, WIN)
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
}


