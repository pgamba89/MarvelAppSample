package com.example.marvelApp.ui.marvelDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.marvelApp.data.model.Character
import com.example.marvelApp.repository.MarvelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarvelDetailModelView @ViewModelInject constructor(
    private val repository: MarvelRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _characterSelected = MutableLiveData<Character>()

    val characterSelected: LiveData<Character>
        get() = _characterSelected

    init {
        val character = savedStateHandle.get<Character>("characterSelected")!!
        getCharacterById(character.id)
    }

    private fun getCharacterById(characterId: String?) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getCharacterById(characterId)
        _characterSelected.postValue(result.data?.results?.get(0))
    }
}