package com.dpulgarin.marvellist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dpulgarin.marvellist.application.AppConstants
import com.dpulgarin.marvellist.core.Resource
import com.dpulgarin.marvellist.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*

class CharacterViewModel(private val repo: CharacterRepository): ViewModel() {

    fun fetchMainScreenCharacters() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())

        kotlin.runCatching {
            repo.getCharacters(createTimestamp(), createHash(createTimestamp()))
        }.onSuccess { characterDataWraper ->
            emit(Resource.Success(characterDataWraper))
        }.onFailure { throwable ->
            emit(Resource.Failure(Exception(throwable.message)))
        }
    }

    fun createTimestamp() = Date().time
    fun createHash(timestamp: Long) = (timestamp.toString() + AppConstants.PRIVATE_KEY + AppConstants.API_KEY).md5()

    fun String.md5(): String = MessageDigest.getInstance("MD5").digest(
        this.toByteArray(
            StandardCharsets.UTF_8
        )
    ).toHex()

    fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }
}

class CharacterViewModelFactory(private val repo: CharacterRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CharacterRepository::class.java).newInstance(repo)
    }
}

