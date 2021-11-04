package com.dpulgarin.marvellist.presentation

import androidx.lifecycle.*
import com.dpulgarin.marvellist.application.AppConstants
import com.dpulgarin.marvellist.core.Resource
import com.dpulgarin.marvellist.core.extensions.md5
import com.dpulgarin.marvellist.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import java.util.*
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repo: CharacterRepository): ViewModel() {

    fun fetchMainScreenCharacters() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())

        kotlin.runCatching {
            repo.getCharacters(createTimestamp(), createHash(createTimestamp()))
        }.onSuccess { characterList ->
            emit(Resource.Success(characterList))
        }.onFailure { throwable ->
            emit(Resource.Failure(Exception(throwable.message)))
        }
    }

    fun fetchDetailScreenCharacterById(characterId: Int) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())

        kotlin.runCatching {
            repo.getCharacterById(characterId, createTimestamp(), createHash(createTimestamp()))
        }.onSuccess { characterDataWraper ->
            emit(Resource.Success(characterDataWraper))
        }.onFailure { throwable ->
            emit(Resource.Failure(Exception(throwable.message)))
        }
    }

    fun createTimestamp() = Date().time
    fun createHash(timestamp: Long) = (timestamp.toString() + AppConstants.PRIVATE_KEY + AppConstants.API_KEY).md5()
}

