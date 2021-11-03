package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.application.AppConstants
import com.dpulgarin.marvellist.application.AppConstants.API_KEY
import com.dpulgarin.marvellist.application.AppConstants.PRIVATE_KEY
import com.dpulgarin.marvellist.data.models.CharacterDataWrapper
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*

interface WebService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") ts: Long,
        @Query("hash") hash: String
    ): CharacterDataWrapper

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Query("apikey") apiKey: String,
        @Query("characterId") characterId: String,
        @Query("ts") ts: Long,
        @Query("hash") hash: String
    ): CharacterDataWrapper

    object RetrofitClient {
        val webService by lazy {
            Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(WebService::class.java)
        }

    }
}