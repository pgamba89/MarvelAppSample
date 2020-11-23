package com.example.marvelApp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelApp.api.ApiService
import com.example.marvelApp.data.CharactersDataSource
import com.example.marvelApp.model.Character
import com.example.marvelApp.model.CharactersResponse
import com.example.marvelApp.utils.Constants
import com.example.marvelApp.utils.HashGenerator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(private val apiService: ApiService) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = true,
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { CharactersDataSource(apiService) }
        ).flow
    }

    suspend fun getCharacterById(id: String?): CharactersResponse {
        val timestamp = System.currentTimeMillis() / 1000
        val hash: String? = HashGenerator.generate(
            timestamp,
            Constants.API_PRIVATE_KEY,
            Constants.API_PUBLIC_KEY
        )

        return apiService.getCharacterId(id!!, Constants.API_PUBLIC_KEY, hash!!, timestamp)
    }
}