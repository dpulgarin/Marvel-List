package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.data.models.CharacterDataWrapper
import com.dpulgarin.marvellist.data.remote.RemoteCharacterDatasource

class CharacterRepositoryImpl(
    private val dataSourceRemote: RemoteCharacterDatasource
) : CharacterRepository {
    override suspend fun getCharacters(ts: Long, hash: String): CharacterDataWrapper {
        return dataSourceRemote.getCharacters(ts, hash)
    }

    override suspend fun getCharacterById(characterId: String, ts: Long, hash: String): CharacterDataWrapper {
        return dataSourceRemote.getCharacterById(characterId, ts, hash)
    }
}