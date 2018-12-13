package io.munros.ext

import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.model.CsvData
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

fun String?.log() {
    println(this)

}

/**
 *
 * CSV may have different categories for Munros so map them
 *
 */
fun String?.toMunroCategory(munroKey: String, topKey: String): MunroCategory? {
    return when (this) { munroKey -> MunroCategory.Munro; topKey -> MunroCategory.Top; else -> null }

}

/**
 *
 * Getting all this out the way
 *
 */
fun String?.importMunrosFromCsv(): ArrayList<CsvData>? {
    var fileReader: BufferedReader? = null

    try {
        val munros = ArrayList<CsvData>()
        var line: String?

        fileReader = BufferedReader(FileReader(this))

        // Read CSV header
        fileReader.readLine()

        // Read the file line by line starting from the second line
        line = fileReader.readLine()

        while (line != null) {
            val values = parseLine(line, ',')

            if (values.isNotEmpty() && values[Name]!!.isNotEmpty()) {
                val csvData = CsvData(
                    values[Running_No],
                    values[DoBIH_Number],
                    values[Streetmap],
                    values[Geograph],
                    values[Hill_bagging],
                    values[Name],
                    values[SMC_Section],
                    values[RHB_Section],
                    values[_Section],
                    values[Height_m]?.toDoubleOrNull(),
                    values[Height_ft],
                    values[Map_1_50],
                    values[Map_1_25],
                    values[Grid_Ref],
                    values[GridRefXY],
                    values[xcoord],
                    values[ycoord],
                    values[date_1891],
                    values[date_1921],
                    values[date_1933],
                    values[date_1953],
                    values[date_1969],
                    values[date_1974],
                    values[date_1981],
                    values[date_1984],
                    values[date_1990],
                    values[date_1997],
                    values[Post_1997]?.toMunroCategory(MUNRO, TOP),
                    null
                )

                munros.add(csvData)

            }

            line = fileReader.readLine()

        }

        return munros

    } catch (e: Exception) {
        "Reading CSV Error!".log()
        e.message?.log()

    } finally {
        try {
            fileReader!!.close()

        } catch (e: IOException) {
            "Closing fileReader Error!".log()
            e.message?.log()

        }

    }

    return null


}

private fun parseLine(line: String, separator: Char) : List<String?> {
    val result = mutableListOf<String?>()
    val builder = StringBuilder()
    var quotes = 0

    for (ch in line) {

        when {
            ch == '\"' -> {
                quotes++
                builder.append(ch)

            }

            (ch == '\n') || (ch ==  '\r') -> {


            }

            (ch == separator) && (quotes % 2 == 0) -> {
                result.add(builder.toString())
                builder.setLength(0)
            }

            else -> builder.append(ch)

        }

    }

    return result

}

// Indexes
private const val Running_No: Int = 0
private const val DoBIH_Number: Int = 1
private const val Streetmap: Int = 2
private const val Geograph: Int = 3
private const val Hill_bagging: Int = 4
private const val Name: Int = 5
private const val SMC_Section: Int = 6
private const val RHB_Section: Int = 7
private const val _Section: Int = 8
private const val Height_m: Int = 9
private const val Height_ft: Int = 10
private const val Map_1_50: Int = 11
private const val Map_1_25: Int = 12
private const val Grid_Ref: Int = 13
private const val GridRefXY: Int = 14
private const val xcoord: Int = 15
private const val ycoord: Int = 16
private const val date_1891: Int = 17
private const val date_1921: Int = 18
private const val date_1933: Int = 19
private const val date_1953: Int = 20
private const val date_1969: Int = 21
private const val date_1974: Int = 22
private const val date_1981: Int = 23
private const val date_1984: Int = 24
private const val date_1990: Int = 25
private const val date_1997: Int = 26
private const val Post_1997: Int = 27
private const val Comments: Int = 28

private const val TOP = "TOP"
private const val MUNRO = "MUN"