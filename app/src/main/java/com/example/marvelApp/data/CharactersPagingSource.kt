package com.example.marvelApp.data

import androidx.paging.PagingSource
import com.example.marvelApp.data.model.Character
import com.example.marvelApp.data.source.remote.ApiService
import com.example.marvelApp.utils.Constants
import com.example.marvelApp.utils.HashGenerator
import retrofit2.HttpException
import java.io.IOException

class CharactersDataSource(private val apiService: ApiService) :
    PagingSource<Int, Character>() {

    private val timestamp = System.currentTimeMillis() / 1000
    private val hash: String? = HashGenerator.generate(
        timestamp,
        Constants.API_PRIVATE_KEY,
        Constants.API_PUBLIC_KEY
    )

    var initialLoadSize = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPageNumber = params.key ?: 1

            if (params.key == null) {
                initialLoadSize = params.loadSize
            }

            val offsetCalc = {
                if (nextPageNumber == 2)
                    initialLoadSize
                else
                    ((nextPageNumber - 1) * params.loadSize) + (initialLoadSize - params.loadSize)
            }
            val offset = offsetCalc.invoke()

            val response = apiService.getCharacters(
                Constants.API_PUBLIC_KEY, hash!!, timestamp,
                params.loadSize, offset
            )

            val characters = response.data!!.results
            val count = characters!!.size

            LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = if (count < params.loadSize) null else nextPageNumber + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}