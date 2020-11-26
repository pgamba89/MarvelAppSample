package com.example.marvelApp.ui.marvelList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelApp.data.model.Character
import com.example.marvelApp.repository.MarvelRepository

class MarvelListModelView @ViewModelInject constructor(private val repository: MarvelRepository) :
    ViewModel() {

    var marvelCharacters: LiveData<PagingData<Character>>? = null

    private val _errorConnection = MutableLiveData<Boolean>()

    val errorConnection : LiveData<Boolean>
        get() = _errorConnection

    init {
        getCharacters()
    }

    private fun getCharacters() {
        try {
            val newResultLiveData: LiveData<PagingData<Character>> =
                repository.getCharacters().cachedIn(viewModelScope)
            marvelCharacters = newResultLiveData
            _errorConnection.value = false
        } catch (e: Exception) {
            _errorConnection.value = true
        }
    }
}