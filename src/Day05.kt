fun main() {

    fun part1(input: List<String>): Int {
        val separator = input.indexOfFirst { it.isEmpty() }
        val rules = input.subList(0, separator).map {
            it.split('|').map { it.toInt() }
        }.map { it[0] to it[1] }
        val pageLists = input.subList(separator + 1, input.size).map {
            it.split(',').map { it.toInt() }
        }
        return pageLists.filter { list ->
            rules.all { (a, b) ->
                a !in list || b !in list || list.indexOf(a) < list.indexOf(b)
            }
        }.sumOf { it[it.size / 2] }
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)

    val input = readInput("Day05")
    part1(input).println()
}
