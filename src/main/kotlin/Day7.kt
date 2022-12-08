import util.loadInput

fun main() {
    val day7 = Day7()
    val input = loadInput(7).filter { it.isNotEmpty() }
    println(day7.first(input))
    println(day7.second())
}

class Day7 {
    private val allDirs = mutableSetOf(Folder("root", null, mutableListOf(), 0))
    fun first(input: List<String>): Int {
        addAllDirsToList(input)
        return allDirs.filter { it.size <= 100000 }.sumOf { it.size }
    }

    fun second(): Int {
        val totalSize = 70000000
        val updateSize = 30000000
        return allDirs
            .map { it.size }
            .sorted()
            .first { totalSize - allDirs.first { it.name == "root" }.size + it >= updateSize }
    }

    private fun addAllDirsToList(input: List<String>) {
        var currDir = allDirs.first()
        input.drop(1).forEach { command ->
            when {
                command == "$ cd .." -> currDir = currDir.parent!!
                command.contains("$ cd") -> currDir = currDir.kids.find { it?.name == command.substring(4).trim() }!!
                command.startsWith("dir") -> currDir.kids += Folder(command.substring(4), currDir, mutableListOf(), 0)
                command.first().isDigit() -> addSize(currDir, command.substringBefore(" ").toInt())
            }
        }
        addFoldersToList(allDirs.first())
    }

    private fun addFoldersToList(folder: Folder) {
        allDirs.add(folder)
        folder.kids.forEach {
            addFoldersToList(it!!)
        }
    }

    private fun addSize(folder: Folder, size: Int) {
        if (folder.parent != null) {
            addSize(folder.parent!!, size)
        }
        folder.size += size
    }


    data class Folder(val name: String, var parent: Folder?, val kids: MutableList<Folder?>, var size: Int) {

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

}
