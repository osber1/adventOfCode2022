import util.loadInput

fun main() {
    val day8 = Day8(loadInput(8).filter { it.isNotEmpty() })
    println(day8.first())
    println(day8.second())
}

class Day8(input: List<String>) {
    private val map = input.map { it.map { it.digitToInt() } }
    private val columnLength = map.size
    private val rowLength = map.first().size
    private val sumArray = Array(columnLength) { BooleanArray(rowLength) }

    fun first(): Int {
        for (row in 0 until columnLength) {
            findVisibleFromLeft(rowLength, row)
            findVisibleFromRight(rowLength, row)
        }
        for (col in 0 until rowLength) {
            findVisibleFromTop(columnLength, col)
            findVisibleFromBottom(columnLength, col)
        }
        return sumArray.sumOf { it.count { it } }
    }

    fun second(): Int {
        var sum = 0
        for (row in 0 until columnLength) {
            for (col in 0 until rowLength) {
                var left = col
                for (i in col - 1 downTo 0) {
                    if (map[row][i] >= map[row][col]) {
                        left = col - i
                        break
                    }
                }
                var rigth = rowLength - col - 1
                for (i in col + 1 until rowLength) {
                    if (map[row][i] >= map[row][col]) {
                        rigth = i - col
                        break
                    }
                }
                var top = row
                for (i in row - 1 downTo 0) {
                    if (map[i][col] >= map[row][col]) {
                        top = row - i
                        break
                    }
                }
                var down = columnLength - row - 1
                for (i in row + 1 until columnLength) {
                    if (map[i][col] >= map[row][col]) {
                        down = i - row
                        break
                    }
                }
                val f = left * rigth * top * down
                if (f > sum) sum = f
            }
        }
        return sum
    }

    private fun findVisibleFromLeft(rowLength: Int, row: Int) {
        var height = -1
        for (col in 0 until rowLength) {
            if (map[row][col] > height) {
                height = map[row][col]
                sumArray[row][col] = true
            }
        }
    }

    private fun findVisibleFromRight(rowLength: Int, row: Int) {
        var height = -1
        for (col in rowLength - 1 downTo 0) {
            if (map[row][col] > height) {
                height = map[row][col]
                sumArray[row][col] = true
            }
        }
    }

    private fun findVisibleFromTop(columnLength: Int, col: Int) {
        var height = -1
        for (row in 0 until columnLength) {
            if (map[row][col] > height) {
                height = map[row][col]
                sumArray[row][col] = true
            }
        }
    }

    private fun findVisibleFromBottom(columnLength: Int, col: Int) {
        var height = -1
        for (row in columnLength - 1 downTo 0) {
            if (map[row][col] > height) {
                height = map[row][col]
                sumArray[row][col] = true
            }
        }
    }

}