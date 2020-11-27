package com.example.marvelApp.ui.marvelList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.marvelApp.data.model.Character
import com.example.marvelApp.repository.MarvelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MarvelListModelViewTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Spy
    private var charactersLiveData: MutableLiveData<PagingData<Character>> = MutableLiveData()

    @Mock
    private lateinit var repository: MarvelRepository

    lateinit var modelView: MarvelListModelView

    private val characters = listOf(
        Character("1", "AAA"),
        Character("2", "BBB")
    )

    @Before
    fun setup() = runBlocking{
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getCharacters()).thenReturn(charactersLiveData)
        modelView = MarvelListModelView(repository)
    }

    @Test
    fun getAllCharacters_WhenCharacters_NotEmpty() = runBlocking{
        modelView.errorConnection.observeForever { }
        modelView.marvelCharacters?.observeForever { }

        charactersLiveData.value = PagingData.from(characters)

        assertEquals(false, modelView.errorConnection.value)
        assertEquals(charactersLiveData.value, modelView.marvelCharacters?.value)
    }
}