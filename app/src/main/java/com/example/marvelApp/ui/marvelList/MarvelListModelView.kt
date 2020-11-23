package com.example.marvelApp.ui.marvelList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelApp.model.Character
import com.example.marvelApp.repository.MarvelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MarvelListModelView @ViewModelInject constructor(private val repository: MarvelRepository) :
    ViewModel() {

    // private var currentResultLiveData: LiveData<PagingData<ModelResponse.Character>>? = null

 //   private val _marvelCharacters = MutableLiveData<PagingData<Character>>()

     private var marvelCharacters: Flow<PagingData<Character>>? = null
   //     get() = _marvelCharacters

    fun getCharacters(): Flow<PagingData<Character>> {
        val newResultLiveData: Flow<PagingData<Character>> =
            repository.getCharacters().cachedIn(viewModelScope)
        marvelCharacters = newResultLiveData
        return newResultLiveData
    }
}