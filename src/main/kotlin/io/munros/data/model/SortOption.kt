package io.munros.data.model

import io.munros.data.enums.SortDirection

data class SortOption constructor(
    val sortableType: Any?,
    val sortDirection: SortDirection?,
    val maxHeight: Int? = 0,
    val minHeight: Int? = 0,
    val take: Int = 0

)