package days

import java.lang.StringBuilder

abstract class Day(val dayOfMonth: Short) {
    fun solve() {
        println(StringBuilder().apply {
            appendLine("---------------")
            appendLine("|   Day ${dayToString()}    |")
            appendLine("---------------")
        }.toString().trim())
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
    }

    abstract fun part1(): Int
    abstract fun part2(): Int
}