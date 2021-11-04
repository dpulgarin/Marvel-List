package com.dpulgarin.marvellist.data.local

import com.dpulgarin.marvellist.data.models.Character
import com.dpulgarin.marvellist.data.models.CharacterEntity
import com.dpulgarin.marvellist.data.models.toCharacterList

class LocalCharacterDataSource(private val characterDao: CharacterDao) {
    suspend fun getCharacters(): List<Character> {
        return characterDao.getAllCharacters().toCharacterList()
    }

    suspend fun saveCharacter(character: CharacterEntity) {
        characterDao.saveCharacter(character)
    }
}