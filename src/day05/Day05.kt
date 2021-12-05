package days.day05

import days.Day
import days.input

fun main() = Day05.solve()

object Day05 : Day(5) {

    override fun part1(): Int {
        input.map { Line.get(it) }.forEach { line ->
            line.points.forEach { point ->
                point.crossed++
            }
        }
        return Point.allPoints.count { it.crossed >= 2 }
    }

    override fun part2(): Int {
        TODO("Not yet implemented")
    }
}

class Point(val x: Int, val y: Int, var crossed: Int = 0) {
    init {
        allPoints += this
    }

    companion object {
        val allPoints = mutableListOf<Point>()

        fun get(x: Int, y: Int) = allPoints.firstOrNull { it.x == x && it.y == y } ?: Point(x, y)

        // X,Y
        fun get(string: String): Point {
            val coordinates = string.split(',').map { it.toInt() }
            return get(coordinates[0], coordinates[1])
        }
    }
}

class Line(val point1: Point, val point2: Point) {
    companion object {
        // X,Y -> X,Y
        fun get(input: String): Line {
            val (point1, point2) = input.split(" -> ").map { Point.get(it) }
            return Line(point1, point2)
        }
    }

    private val isDiagonal = point1.x != point2.x || point1.y != point2.y

    val points: List<Point> get() =
        when {
            point1.x == point2.x -> (point1.y..point2.y).map { Point.get(point1.x, it) }
            point1.y == point2.y -> (point1.x..point2.x).map { Point.get(it, point1.y) }
            else -> listOf()
        }
}
