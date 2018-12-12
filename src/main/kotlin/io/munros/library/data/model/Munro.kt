package io.munros.library.data.model

import io.munros.library.data.enums.MunroCategory

/**
 *
 * Munro data model to display our finished results
 *
 */

data class Munro constructor(
    val name: String?,
    val height: Double?,
    val munroCategory: MunroCategory?,
    val gridRef: String?
)