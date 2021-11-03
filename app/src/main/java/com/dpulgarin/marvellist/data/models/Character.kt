package com.dpulgarin.marvellist.data.models

import java.util.*

data class Character (
    val id: Int = -1,
    val name: String = "",
    val description: String = "",
    val modified: Date = Date(),
    val resourceURI: String = "",
    val urls: List<Url> = listOf(),
    val thumbnail: Image = Image(),
    val comics: ComicList = ComicList(),
    val stories: StoryList = StoryList(),
    val events: EventList = EventList(),
    val series: SeriesList = SeriesList(),
)