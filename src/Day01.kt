import kotlin.math.absoluteValue

fun main() {
    fun lists(input: List<String>): Pair<List<Int>, List<Int>> {
        val splits = input.map { it.split("\\s+".toRegex()) }
        val list1 = splits.map { it[0].toInt() }.sorted()
        val list2 = splits.map { it[1].toInt() }.sorted()
        return Pair(list1, list2)
    }

    fun part1(input: List<String>): Int {
        val (list1, list2) = lists(input)
        return (list1 zip list2).sumOf { (a, b) -> (a - b).absoluteValue }
    }

    fun part2(input: List<String>): Int {
        val (list1, list2) = lists(input)
        val counts = list2.groupingBy { it }.eachCount()
        return list1.sumOf { it * counts.getOrDefault(it, 0) }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
