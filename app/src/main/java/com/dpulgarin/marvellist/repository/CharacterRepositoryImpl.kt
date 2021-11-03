package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.data.models.CharacterDataWrapper
import com.dpulgarin.marvellist.data.remote.RemoteCharacterDatasource

class CharacterRepositoryImpl(
    private val dataSourceRemote: RemoteCharacterDatasource
) : CharacterRepository {
    override suspend fun getCharacters(): CharacterDataWrapper {
        return dataSourceRemote.getCharacters()
    }

    override suspend fun getCharacterById(characterId: String): CharacterDataWrapper {
        return dataSourceRemote.getCharacterById(characterId)
    }
}