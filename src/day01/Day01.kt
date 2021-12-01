package days.day01

import days.Day
import days.input

fun main() {
    Day01.solve()
}

object Day01 : Day(1) {

    override fun part1() = input.map { it.toInt() }.zipWithNext().count { it.first < it.second }

    override fun part2() = input.map { it.toInt() }.windowed(3)
        .map { it.sum() }.zipWithNext().count { it.first < it.second }
}