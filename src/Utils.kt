package days

import java.io.File

fun Day.getFile() = File("src/day${dayToString()}", "input.txt").apply {
    if (exists()) return this
    if (!parentFile.exists()) parentFile.mkdirs()
    createNewFile()
}

fun Day.dayToString() = "${if (dayOfMonth < 10)"0" else ""}$dayOfMonth"

val Day.input get() = getFile().readLines()