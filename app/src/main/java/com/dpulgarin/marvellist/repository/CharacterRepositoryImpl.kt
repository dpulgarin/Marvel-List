package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.core.InternetCheck
import com.dpulgarin.marvellist.data.local.LocalCharacterDataSource
import com.dpulgarin.marvellist.data.models.Character
import com.dpulgarin.marvellist.data.models.CharacterDataWrapper
import com.dpulgarin.marvellist.data.models.toCharacterEntity
import com.dpulgarin.marvellist.data.remote.RemoteCharacterDatasource
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dataSourceRemote: RemoteCharacterDatasource,
    private val dataSourceLocal: LocalCharacterDataSource
) : CharacterRepository {
    override suspend fun getCharacters(ts: Long, hash: String): List<Character> {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getCharacters(ts, hash).data.results.forEach { character ->
                dataSourceLocal.saveCharacter(character.toCharacterEntity())
            }
            return dataSourceLocal.getCharacters()
        } else {
            return dataSourceLocal.getCharacters()
        }
    }

    override suspend fun getCharacterById(characterId: Int, ts: Long, hash: String): CharacterDataWrapper {
        return dataSourceRemote.getCharacterById(characterId, ts, hash)
    }
}