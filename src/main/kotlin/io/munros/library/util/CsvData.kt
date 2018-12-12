package io.munros.library.util

import io.munros.library.data.enums.MunroCategory

/**
 *
 * Container for the CSV data we want to sort/filter
 *
 */

data class CsvData constructor(
    val Running_No: String?,
    val DoBIH_Number: String?,
    val Streetmap: String?,
    val Geograph: String?,
    val Hill_bagging: String?,
    val Name: String?,
    val SMC_Section: String?,
    val RHB_Section: String?,
    val _Section: String?,
    val Height_m: Double?,
    val Height_ft: String?,
    val Map_1_50: String?,
    val Map_1_25: String?,
    val Grid_Ref: String?,
    val GridRefXY: String?,
    val xcoord: String?,
    val ycoord: String?,
    val date_1891: String?,
    val date_1921: String?,
    val date_1933: String?,
    val date_1953: String?,
    val date_1969: String?,
    val date_1974: String?,
    val date_1981: String?,
    val date_1984: String?,
    val date_1990: String?,
    val date_1997: String?,
    val post_1997: MunroCategory?,
    val Comments: String?
)