package io.munros.library

import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.util.CsvData
import io.munros.library.data.model.Munro
import io.munros.library.util.MunroException

/**
 *
 * Library consists of a set of extension methods for lists of type [CsvData]
 *
 * This allows chaining of methods to easily get the desired set of filters/sort options
 *
 * Each chain should end with [makeNice] to convert the list into a list of [Munro] items
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

        null -> this?.sortedBy { x -> x.name }

    }

}


fun List<CsvData>?.filterHeights(minHeight: Double, maxHeight: Double): List<CsvData>? {
    if (minHeight > maxHeight) { throw MunroException("The minimum height cannot be higher than the maximum") }

    return this?.filter { x -> x.heightMetres != null && x.heightMetres >= minHeight && x.heightMetres <= maxHeight }

}


fun List<CsvData>.makeNice(): List<Munro>? {
    val resultList = ArrayList<Munro>()

    this.forEach { munro ->
        resultList.add(Munro(munro.name, munro.heightMetres, munro.post1997, munro.gridRef))

    }

    return resultList

}

