package com.dpulgarin.marvellist.data.remote

import com.dpulgarin.marvellist.application.AppConstants
import com.dpulgarin.marvellist.data.models.CharacterDataWrapper
import com.dpulgarin.marvellist.repository.WebService

class RemoteCharacterDatasource(private val webService: WebService) {
    suspend fun getCharacters(ts: Long, hash: String): CharacterDataWrapper = webService.getCharacters(AppConstants.API_KEY, ts, hash)

    suspend fun getCharacterById(characterId: String, ts: Long, hash: String): CharacterDataWrapper = webService.getCharacterById(AppConstants.API_KEY, characterId, ts, hash)
}