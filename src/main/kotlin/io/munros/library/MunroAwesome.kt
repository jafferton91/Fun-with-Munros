package io.munros.library

import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.data.model.Munro
import io.munros.library.data.model.CsvData
import io.munros.library.exception.MunroException

/**
 *
 * Library consists of a set of extension methods for lists of type [CsvData]
 *
 * This allows chaining of methods to easily get the desired set of filters/sort options
 *
 * Each chain should end with [endChain] to convert the list into a list of [Munro] items
 *
 * [sortByCategory], [sortByName], [sortByHeight], [filterHeights] are single use methods
 *
 * [createAscChain], [createDescChain] are designed to take multiple fields to sort the data
 *
 */

fun List<CsvData>?.sortByCategory(munroCategory: MunroCategory?): List<CsvData>? {
    return when (munroCategory) {
        MunroCategory.Munro -> this?.filter { x -> x.post1997 == MunroCategory.Munro }
        MunroCategory.Top -> this?.filter { x -> x.post1997 == MunroCategory.Top }
        MunroCategory.Either -> this?.filter { x -> x.post1997 != null }

        else -> { this }

    }

}

fun List<CsvData>?.sortByName(sortDirection: SortDirection?): List<CsvData>? {
    return when (sortDirection) {
        SortDirection.Asc -> this?.sortedBy { x -> x.name }
        SortDirection.Desc -> this?.sortedByDescending { x -> x.name }

        null -> this?.sortedBy { x -> x.name }

    }

}

fun List<CsvData>?.sortByHeight(sortDirection: SortDirection?): List<CsvData>? {
    return when (sortDirection) {
        SortDirection.Asc -> this?.sortedBy { x -> x.heightMetres }
        SortDirection.Desc -> this?.sortedByDescending { x -> x.heightMetres }

        null -> this?.sortedBy { x -> x.heightMetres }

    }

}

fun List<CsvData>?.filterHeights(minHeight: Double, maxHeight: Double): List<CsvData>? {
    validateHeights(minHeight, maxHeight)
    return this?.filter { x -> x.heightMetres != null && x.heightMetres >= minHeight && x.heightMetres <= maxHeight }

}

/**
 *
 * Check for likely mistake
 *
 */
private fun validateHeights(minHeight: Double, maxHeight: Double) {
    if (minHeight > maxHeight) { throw MunroException("The minimum height cannot be higher than the maximum") }

}

/**
 *
 * Wrap std lib sorted with to create chained asc filtering.
 *
 * Supporting 2 fields for this
 *
 */
fun List<CsvData>?.createAscChain(vararg selectors: (CsvData) -> Comparable<*>?): List<CsvData>? {
    validateFields(selectors.size)
    return this?.sortedWith(compareBy(selectors[0], selectors[1]))

}

/**
 *
 * Create chained desc filtering.
 *
 */
fun List<CsvData>?.createDescChain(vararg selectors: (CsvData) -> Comparable<*>?): List<CsvData>? {
    validateFields(selectors.size)
    return this?.sortedWith(compareByDescending(selectors[0]).thenByDescending(selectors[1]))

}

/**
 *
 * Throw if we have anything other than 2 fields
 *
 */
private fun validateFields(fieldCount: Int) {
    if (fieldCount != 2) { throw MunroException("You must use 2 fields for this filter") }

}

/**
 *
 * This method should be called at the end of any chain or by itself
 *
 */
fun List<CsvData>.endChain(): List<Munro>? {
    val resultList = ArrayList<Munro>()

    this.forEach { munro ->
        if (munro.post1997 != null) {
            resultList.add(Munro(munro.name, munro.heightMetres, munro.post1997, munro.gridRef))

        }

    }

    return resultList

}

