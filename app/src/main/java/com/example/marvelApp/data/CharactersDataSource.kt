package com.example.marvelApp.data

import androidx.paging.PagingSource
import com.example.marvelApp.api.ApiService
import com.example.marvelApp.model.Character
import com.example.marvelApp.utils.Constants
import com.example.marvelApp.utils.HashGenerator
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX  = 1

class CharactersDataSource(private val apiService: ApiService) :
    PagingSource<Int, Character>() {

    private val timestamp = System.currentTimeMillis() / 1000
    private val hash: String? = HashGenerator.generate(
        timestamp,
        Constants.API_PRIVATE_KEY,
        Constants.API_PUBLIC_KEY
    )

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getCharacters(Constants.API_PUBLIC_KEY, hash!!, timestamp,
                params.loadSize, page)
            val characters = response.data!!.results
            LoadResult.Page(
                data = characters!!,
                prevKey = if (page == STARTING_PAGE_INDEX ) null else page - 1,
                nextKey = if (characters.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}