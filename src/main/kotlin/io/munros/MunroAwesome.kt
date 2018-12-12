package io.munros

import io.munros.data.enums.MunroCategory
import io.munros.data.enums.SortDirection
import io.munros.data.model.Munro

private const val TOP = "TOP"
private const val MUNRO = "MUN"

data class MunroResult constructor(val name: String?, val height: String?, val munroCategory: MunroCategory?, val gridRef: String?)

fun List<Munro>?.sortByCategory(munroCategory: MunroCategory?): List<Munro>? {
    val munros = this

    return when (munroCategory) {
        MunroCategory.Munro -> munros?.filter { x -> x.post_1997?.equals(MUNRO, true) ?: false }
        MunroCategory.Top -> munros?.sortedBy { x -> x.post_1997?.equals(TOP, true) }
        MunroCategory.Either -> munros?.sortedBy { x -> x.post_1997?.isNotEmpty() }

        null -> munros?.sortedBy { x -> x.post_1997?.isNotEmpty() }

    }

}

fun List<Munro>?.sortByName(sortDirection: SortDirection?): List<Munro>? {
    val munros = this

    return when (sortDirection) {
        SortDirection.Asc -> munros?.sortedBy { x -> x.Name }
        SortDirection.Desc -> munros?.sortedByDescending { x -> x.Name }

        null -> munros?.sortedBy { x -> x.Name }

    }

}

fun List<Munro>.makeNice(): List<MunroResult>? {
    val resultList = ArrayList<MunroResult>()

    this.forEach { munro ->
        val category = when (munro.post_1997) { MUNRO -> MunroCategory.Munro; TOP -> MunroCategory.Top; else -> null } // todo change munro model category/types
        resultList.add(MunroResult(munro.Name, munro.Height_m, category, munro.Grid_Ref))

    }

    return resultList

}

