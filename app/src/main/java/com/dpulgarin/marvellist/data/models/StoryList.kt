package com.dpulgarin.marvellist.data.models

data class StoryList (
    val available: Int = -1,
    val returned: Int = -1,
    val collectionURI: String = "",
    val items: List<StorySummary> = listOf(),
)