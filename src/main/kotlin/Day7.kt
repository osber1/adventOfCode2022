import util.loadInput

fun main() {
    val day7 = Day7()
    val input = loadInput(7).filter { it.isNotEmpty() }
    println(day7.first(input))
    println(day7.second(input))
}

class Day7 {
    private val allDirs = mutableSetOf(Structure("root", null, mutableListOf(), 0))
    fun first(input: List<String>): Int {
        addAllDirsToList(input)
        return allDirs.filter { it.size <= 100000 }.sumOf { it.size }
    }

    fun second(input: List<String>): Int {
        val totalSize = 70000000
        val updateSize = 30000000
        return allDirs
            .map { it.size }
            .sorted()
            .first { totalSize - allDirs.first { it.name == "root" }.size + it >= updateSize }
    }

    private fun addAllDirsToList(input: List<String>) {
        var currDir = allDirs.first()
        input.forEach { command ->
            if (command.contains("$ cd ..")) {
                currDir = currDir.parent!!
            }

            if (command.contains("$ cd") && !command.contains("..") && !command.contains("/")) {
                currDir = currDir.kids.find { it?.name == command.substring(4).trim() }!!
            }

            if (command.startsWith("dir")) {
                currDir.addKid(Structure(command.substring(4), currDir, mutableListOf(), 0))
            }

            val regexMatchList = "\\d+".toRegex().findAll(command).map { it.value.toInt() }.toList()
            if (regexMatchList.isNotEmpty()) {
                val size = regexMatchList.first()
                addSize(currDir, size)
            }
        }
        addFoldersToList(allDirs.first())
    }

    private fun addFoldersToList(structure: Structure) {
        allDirs.add(structure)
        structure.kids.forEach {
            addFoldersToList(it!!)
        }
    }

    private fun addSize(structure: Structure, size: Int) {
        if (structure.parent != null) {
            addSize(structure.parent!!, size)
        }
        structure.size += size
    }


    data class Structure(val name: String, var parent: Structure?, val kids: MutableList<Structure?>, var size: Int) {
        fun addKid(kid: Structure) {
            kids.add(kid)
        }

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

}
