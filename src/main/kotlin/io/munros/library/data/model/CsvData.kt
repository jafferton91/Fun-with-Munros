package io.munros.library.data.model

import io.munros.library.data.enums.MunroCategory

/**
 *
 * Container for the CSV data we want to sort/filter
 *
 */

data class CsvData constructor(
    val runningNumber: String?,
    val DoBIHNumber: String?,
    val streetMap: String?,
    val geograph: String?,
    val hillBagging: String?,
    val name: String?,
    val SmcSection: String?,
    val RhbSection: String?,
    val section: String?,
    val heightMetres: Double?,
    val heightFeet: String?,
    val map150: String?,
    val map125: String?,
    val gridRef: String?,
    val gridRefXY: String?,
    val x: String?,
    val y: String?,
    val date1891: String?,
    val date1921: String?,
    val date1933: String?,
    val date1953: String?,
    val date1969: String?,
    val date1974: String?,
    val date1981: String?,
    val date1984: String?,
    val date1990: String?,
    val date1997: String?,
    val post1997: MunroCategory?,
    val comments: String?

)