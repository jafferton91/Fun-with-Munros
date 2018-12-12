package io.munros

import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.util.CsvData
import io.munros.ext.log
import io.munros.ext.toMunroCategory
import io.munros.library.*
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

fun main() {

    // Up to you to get data from whatever munro csv and map to munro model
    // pros - use csv library of choice, use different csv file
    // cons - manual work for user
    val munros = importMunrosFromCsv("libs/munrotab_v6.2.csv")

    // Category sort case -
    // Create our sort options
    val sortCategoryCaseAsc = munros.sortByCategory(MunroCategory.Munro)?.makeNice()
    val sortCategoryCaseDesc = munros.sortByCategory(MunroCategory.Top)?.makeNice()
    val sortCategoryCaseNull = munros.sortByCategory(null)?.makeNice()

    // Name sort case -
    // Create our sort options
    val sortNameCaseAsc = munros.sortByName(SortDirection.Asc)?.makeNice()
    val sortNameCaseDesc = munros.sortByName(SortDirection.Desc)?.makeNice()
    val sortNameCaseNull = munros.sortByName(null)?.makeNice()

    // Height sort case -
    // Create our sort options
    val sortHeightCaseAsc = munros.sortByHeight(SortDirection.Asc)?.makeNice()
    val sortHeightCaseDesc = munros.sortByHeight(SortDirection.Desc)?.makeNice()
    val sortHeightCaseNull = munros.sortByHeight(null)?.makeNice()

    // Min/Max Height filters
    val filterMinHeight = munros.filterHeights(1000.0, 1250.0)?.sortByName(null)?.makeNice()
    val filterMaxHeight = munros.filterHeights(0.0, 1000.0)?.makeNice()

    // Chaining possibilities
    val chainFilters = munros.sortByCategory(MunroCategory.Munro)?.sortByName(SortDirection.Desc)?.makeNice()

    // Use existing methods to limit results and manipulate the list
    val chainFiltersTopTenPre = munros.sortByCategory(MunroCategory.Munro)?.take(10)?.sortByName(SortDirection.Desc)?.makeNice()
    val chainFiltersTopTenPost = munros.sortByCategory(MunroCategory.Munro)?.sortByName(SortDirection.Desc)?.makeNice()?.take(10)

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

private fun importMunrosFromCsv(path: String?): ArrayList<CsvData>? {
    var fileReader: BufferedReader? = null

    try {
        val munros = ArrayList<CsvData>()
        var line: String?

        fileReader = BufferedReader(FileReader(path))

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
                    null)

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