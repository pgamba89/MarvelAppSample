package com.example.marvelApp.ui.marvelDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.example.marvelApp.data.model.Character
import com.example.marvelApp.data.model.CharactersResponse
import com.example.marvelApp.data.model.Data
import com.example.marvelApp.repository.MarvelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MarvelDetailModelViewTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    fun runBlockingTest(
        context: CoroutineContext = EmptyCoroutineContext,
        testBody: suspend TestCoroutineScope.() -> Unit
    ): Unit {
    }

    lateinit var modelView: MarvelDetailModelView

    @Mock
    lateinit var observer: Observer<Character>

    private var handle = SavedStateHandle()

    @Mock
    private lateinit var repository: MarvelRepository

    private val characterResponse =
        CharactersResponse(
            "200", "AAA", "", "", "",
            Data("", "", "", "", listOf(Character("1")))
        )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        handle.set("characterSelected", Character("1"))
        modelView = MarvelDetailModelView(repository, handle)
    }

    @Test
    fun getCharacterByID() = runBlockingTest {
        modelView.characterSelected.observeForever(observer)

        Mockito.`when`(repository.getCharacterById("1")).thenReturn(characterResponse)
        val character = characterResponse.data?.results?.get(0)

        Mockito.verify(observer).onChanged(character)
    }
}