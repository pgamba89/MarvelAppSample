package com.example.marvelApp.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.marvelApp.data.CharactersDataSource
import com.example.marvelApp.data.model.Character
import com.example.marvelApp.data.model.CharactersResponse
import com.example.marvelApp.data.source.remote.ApiService
import com.example.marvelApp.utils.Constants
import com.example.marvelApp.utils.HashGenerator
import com.example.marvelApp.utils.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
@Singleton
class MarvelRepository @Inject constructor(
    private val apiService: ApiService,
) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    fun getCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersDataSource(apiService) }
        ).liveData
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