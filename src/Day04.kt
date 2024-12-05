fun main() {

    fun part1(input: List<String>): Int {
        fun wordStartingAt(coords: Pair<Int, Int>, offset: Pair<Int, Int>) = buildString {
            var (x, y) = coords
            repeat(4) {
                append(input[y][x])
                x += offset.first
                y += offset.second
                if (x !in input[0].indices || y !in input.indices) return@buildString
            }
        }

        var count = 0
        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] != 'X') continue
                for (offset in listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)) {
                    val word = wordStartingAt(x to y, offset)
                    if (word == "XMAS") count++
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        fun lettersCenteringOn(center: Pair<Int, Int>) = buildString {
            val (x, y) = center
            for(offset in listOf(-1 to -1, -1 to 1, 1 to 1, 1 to -1)) {
                if (x + offset.first !in input[0].indices || y + offset.second !in input.indices) return@buildString
                append(input[y + offset.second][x + offset.first])
            }
        }

        var count = 0
        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] != 'A') continue
                val letters = lettersCenteringOn(x to y)
                if (letters in setOf("MMSS", "MSSM", "SSMM", "SMMS")) count++
            }
        }
        return count
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
