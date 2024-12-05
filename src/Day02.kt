import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    fun isSafe(line: String, remove: Boolean): Boolean {
        val levels = line.split(' ').map { it.toInt() }
        val deltas = levels.zipWithNext { a, b -> a - b }
        if (deltas.all { it.sign == deltas.first().sign && it.absoluteValue in 1..3 }) {
            return true
        }
        if (!remove) {
            return false
        }
        return levels.indices.any { i ->
            val newLevels = levels.toMutableList()
            newLevels.removeAt(i)
            val newDeltas = newLevels.zipWithNext { a, b -> a - b }
            newDeltas.all { it.sign == newDeltas.first().sign && it.absoluteValue in 1..3 }
        }
    }

    fun part1(input: List<String>) = input.count { isSafe(it, false) }

    fun part2(input: List<String>) = input.count{ isSafe(it, true) }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
