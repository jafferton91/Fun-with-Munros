package io.munros

import io.munros.ext.importMunrosFromCsv
import io.munros.library.data.enums.MunroCategory
import io.munros.library.endChain
import io.munros.library.sortByCategory

fun main() {

    // Up to you to get data from whatever munro csv and map to munro model
    // pros - use csv library of choice, use different csv file
    // cons - manual work for user
    val munros = "libs/munrotab_v6.2.csv".importMunrosFromCsv()

    // Example basic usage
    val sortCategoryCaseAsc = munros.sortByCategory(MunroCategory.Munro)?.endChain()
    val sortCategoryCaseDesc = munros.sortByCategory(MunroCategory.Top)?.endChain()
    val sortCategoryCaseNull = munros.sortByCategory(null)?.endChain()

}

