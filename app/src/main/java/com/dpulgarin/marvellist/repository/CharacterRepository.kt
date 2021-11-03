package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.data.models.CharacterDataWrapper

interface CharacterRepository {
    suspend fun getCharacters(ts: Long, hash: String): CharacterDataWrapper
    suspend fun getCharacterById(characterId: String, ts: Long, hash: String): CharacterDataWrapper
}