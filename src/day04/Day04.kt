package day04

import days.Day
import days.getFile
import days.input

fun main() = Day04.solve()

object Day04 : Day(4) {
    private val boards = mutableListOf<BingoBoard>()
    private val numbers = input.first().split(",").map { it.toInt() }

    init {
        getFile().readText().split("\n\n").drop(1).forEach {
            boards.add(BingoBoard(it.split("\\s+".toRegex()).filter { s -> s.isNotEmpty() }
                .map { s -> s.toInt() to false }.toMutableList()))
        }
    }

    override fun part1(): Int {
        numbers.forEach { number ->
            boards.forEach { board ->
                board.apply {
                    numberDrawn(number)
                    if (isFinished()) {
                        hasWon = true
                        return unmarkedSum * number
                    }
                }
            }
        }
        error("Wrong :<")
    }

    override fun part2(): Int {
        numbers.forEach { number ->
            boards.forEach { board ->
                board.apply {
                    numberDrawn(number)
                    if (isFinished()) {
                        hasWon = true

                        if (boards.filter { !it.hasWon }.size == 1) {
                            return number * boards.first { !it.hasWon }.fields.filter { !it.second }
                                .sumOf { it.first }
                        }
                    }
                }
            }
        }
        error("Wrong :<")
    }
}

class BingoBoard(val fields: MutableList<Pair<Int, Boolean>>) {
    var hasWon = false
    private fun getRow(row: Int): List<Pair<Int, Boolean>> = fields.slice((row * 5) until (row * 5 + 5))
    private fun getColumn(column: Int): List<Pair<Int, Boolean>> = fields.slice(column until 25 step 5)

    fun numberDrawn(number: Int) {
        fields.firstOrNull { it.first == number }.let { field ->
            if (field != null) {
                if (field.second) return
                val index = fields.indexOf(field)
                fields.remove(field)
                fields.add(index, field.first to true)
            }
        }
    }

    fun reset() {
        fields.forEach {
            it.let { field ->
                if (field.second) return
                val index = fields.indexOf(field)
                fields.remove(field)
                fields.add(index, field.first to true)
            }
        }
        hasWon = false
    }

    fun isFinished(): Boolean {
        (0..4).forEach { i ->
            if (!getRow(i).any { !it.second }) return true
            if (!getColumn(i).any { !it.second }) return true
        }
        return false
    }

    val unmarkedSum get() = fields.filter { !it.second }.sumOf { it.first }
}
