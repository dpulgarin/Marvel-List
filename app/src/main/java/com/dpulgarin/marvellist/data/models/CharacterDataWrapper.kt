package com.dpulgarin.marvellist.data.models

data class CharacterDataWrapper (
    val code: Int = -1,
    val status: String = "",
    val copyright: String = "",
    val attributionText: String = "",
    val attributionHTML: String = "",
    val data: CharacterDataContainer = CharacterDataContainer(),
    val etag: String = "",
)