package days.day01

import days.Day
import days.input

fun main() {
    Day01.solve()
}

object Day01 : Day(1) {

    override fun part1() = input.map(String::toInt).windowed(2).count { it[0] < it[1] }

    override fun part2() = input.map(String::toInt).windowed(3)
        .map { it.sum() }.windowed(2).count { it[0] < it[1] }
}