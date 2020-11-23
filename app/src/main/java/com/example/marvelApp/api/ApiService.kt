package com.example.marvelApp.api

import com.example.marvelApp.model.CharactersResponse
import com.example.marvelApp.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .build()

interface ApiService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timeStamp: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharactersResponse

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterId(
        @Query("characterId") type: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): CharactersResponse
}

object MarvelApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}