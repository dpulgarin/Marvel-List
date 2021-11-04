package com.dpulgarin.marvellist.data.local

import com.dpulgarin.marvellist.data.models.Character
import com.dpulgarin.marvellist.data.models.CharacterEntity
import com.dpulgarin.marvellist.data.models.toCharacterList
import javax.inject.Inject

class LocalCharacterDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend fun getCharacters(): List<Character> {
        return characterDao.getAllCharacters().toCharacterList()
    }

    suspend fun saveCharacter(character: CharacterEntity) {
        characterDao.saveCharacter(character)
    }
}