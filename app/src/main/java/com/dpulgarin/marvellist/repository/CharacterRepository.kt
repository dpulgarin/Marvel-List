package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.data.models.CharacterDataWrapper
import com.dpulgarin.marvellist.data.models.Character

interface CharacterRepository {
    suspend fun getCharacters(ts: Long, hash: String): List<Character>
    suspend fun getCharacterById(characterId: Int, ts: Long, hash: String): CharacterDataWrapper
}