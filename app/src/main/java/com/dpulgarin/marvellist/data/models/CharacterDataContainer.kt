package com.dpulgarin.marvellist.data.models

data class CharacterDataContainer (
    val offset: Int = -1,
    val limit: Int = -1,
    val total: Int = -1,
    val count: Int = -1,
    val results: List<Character> = listOf(),
)