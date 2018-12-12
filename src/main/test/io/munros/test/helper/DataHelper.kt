package io.munros.test.helper

import io.munros.library.data.enums.MunroCategory
import io.munros.library.util.CsvData

// Create easily a csvData entry for test
fun createCsvDataForTest(name: String, height: Double, munroCategory: MunroCategory?): CsvData {
    return CsvData(
        null,
        null,
        null,
        null,
        null,
        name,
        null,
        null,
        null,
        height,
        null,
        null,
        null,
        "GRID5544.S",
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        munroCategory,
        null)

}