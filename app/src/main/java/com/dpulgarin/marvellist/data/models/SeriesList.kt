package com.dpulgarin.marvellist.data.models

data class SeriesList (
    val available: Int = -1,
    val returned: Int = -1,
    val collectionURI: String = "",
    val items: List<SeriesSummary> = listOf(),
)