import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        val pattern = """mul\((\d+),(\d+)\)""".toRegex()
        return input.sumOf { line ->
            val matches = pattern.findAll(line)
            matches.sumOf { match ->
                val (a, b) = match.destructured
                a.toInt() * b.toInt()
            }
        }
    }

    fun part2(input: List<String>): BigInteger {
        val pattern = """mul\((\d+),(\d+)\)|do(?:n't)?\(\)""".toRegex()
        var on = true
        var sum = BigInteger.ZERO
        input.forEach { line ->
            val matches = pattern.findAll(line)
            matches.forEach { matchResult ->
                if (matchResult.groupValues[0].startsWith("mul")) {
                    if (on) {
                        val (a, b) = matchResult.destructured
                        sum += a.toBigInteger() * b.toBigInteger()
                    }
                } else {
                    on = matchResult.groupValues[0] == "do()"
                }
            }
        }
        return sum
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48.toBigInteger())

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
