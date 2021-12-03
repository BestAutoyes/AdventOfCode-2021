package days.day03

import days.Day
import days.input

fun main() = Day03.solve()

object Day03 : Day(3) {

    override fun part1() =
        input.map { it.mapIndexed { index, c -> index to c } }.flatten().groupBy({ it.first }, { it.second })
            .map { it.value.sorted()[it.value.size / 2] }.joinToString("").toInt(2).let {
                it * it.xor(4095)
            }

    override fun part2() = (0 until input[0].length).foldIndexed(input to input) { index, (oxygen, co2), _ ->
        val oxygenBit = if ((oxygen.count { it[index] == '1' }) >= oxygen.size / 2.0) '1' else '0'
        val co2Bit = if ((co2.count { it[index] == '1' }) >= co2.size / 2.0) '1' else '0'

        var pair = oxygen to co2
        if (oxygen.size != 1) pair = oxygen.filter { it[index] == oxygenBit } to pair.second
        if (co2.size != 1) pair = pair.first to co2.filter { it[index] != co2Bit }
        pair
    }.let {
        it.first[0].toInt(2) * it.second[0].toInt(2)
    }
}