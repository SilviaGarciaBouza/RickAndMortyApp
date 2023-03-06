package com.example.rickandmorty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.Every
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RickAndMortyViewModelTest {
    //@ExperimentalCoroutinesApi
    //Si es necesario mokear lo q hay en () en el ViewModel @RelaxedMockK
    @RelaxedMockK
    private lateinit var rickAndMortyApiService: RickAndMortyApiService

    //Instanciar el ViewModel:
    private lateinit var rickAndMortyViewModel: RickAndMortyViewModel

    @get:Rule
    val rule:InstantTaskExecutorRule = InstantTaskExecutorRule()
    //Ejecutar antes de los tests:
   // @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun onBefore(){
        //inicializael mockK
        MockKAnnotations.init(this)
       // rickAndMortyService = RickAndMortyService()
        rickAndMortyViewModel = RickAndMortyViewModel(rickAndMortyApiService)
        //al ser corrutuna y usar el scope:
        Dispatchers.setMain(Dispatchers.Unconfined)
    }



    @Test
    fun whenSuccesfulThenStateIsDone() = runTest {
        //Given
        val listItem= listOf<ItemRickAndMorty>(
            ItemRickAndMorty("https://rickandmortyapi.com/api/character/avatar/1.jpeg", "Rick Sanchez"),
            ItemRickAndMorty("https://rickandmortyapi.com/api/character/avatar/2.jpeg\",\"episode\":[\"https://rickandmortyapi.com/api/episode/1\",\"https://rickandmortyapi.com/api/episode/2\",\"https://rickandmortyapi.com/api/e", "Morty Smith")
        )
        //Sino es una corrutina, suspend, se usa Every
        coEvery { rickAndMortyApiService.getItems().results } returns listItem
        //When
        rickAndMortyViewModel.getCharacteres()
        //Then
        assert(rickAndMortyViewModel.status.value == RickAndMortyStatus.DONE)
    }


    //Ejecutar después de los tests
    // @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun onAfter(){
        //resetea al final
        Dispatchers.resetMain()
    }
}
