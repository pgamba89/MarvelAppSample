package com.example.marvelApp.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.marvelApp.api.ApiService
import com.example.marvelApp.data.CharactersDataSource
import com.example.marvelApp.model.Character
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
    
/*    suspend fun getCharacterById(code: String): Character {
        return api.retrofitService.getCharacterId()
    }*/
}