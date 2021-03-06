package days.day02

import days.Day
import days.input

fun main() = Day02.solve()

object Day02 : Day(2) {

    // !!!!!!!!!!!
    override fun part1() =
        input.map { it.split(' ') }.groupBy({ it.first() }, { it.last().toInt() })
            .let { it["forward"]!!.sum() * (it["down"]!!.sum() - it["up"]!!.sum()) }


    override fun part2(): Int {
        var aim = 0
        var horizontal = 0
        var depth = 0
        input.map { it.split(' ').let { s -> s.first() to s.last().toInt() } }.forEach { (instruction, amount) ->
            when (instruction) {
                "down" -> aim += amount
                "up" -> aim -= amount
                "forward" -> {
                    horizontal += amount
                    depth += aim * amount
                }
            }
        }
        return horizontal * depth
    }
}
