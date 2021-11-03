package com.dpulgarin.marvellist.repository

import com.dpulgarin.marvellist.application.AppConstants
import com.dpulgarin.marvellist.data.models.CharacterDataWrapper
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("api_key") apiKey: String): CharacterDataWrapper

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(@Query("api_key") apiKey: String, @Query("characterId") characterId: String): CharacterDataWrapper

    object RetrofitClient {
        val webService by lazy {
            Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(WebService::class.java)
        }

    }
}