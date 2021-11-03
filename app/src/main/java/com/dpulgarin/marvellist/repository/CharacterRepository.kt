package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.data.models.CharacterDataWrapper

interface CharacterRepository {
    suspend fun getCharacters(): CharacterDataWrapper
    suspend fun getCharacterById(characterId: String): CharacterDataWrapper
}