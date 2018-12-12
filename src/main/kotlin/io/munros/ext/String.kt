package io.munros.ext

import io.munros.library.data.enums.MunroCategory

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