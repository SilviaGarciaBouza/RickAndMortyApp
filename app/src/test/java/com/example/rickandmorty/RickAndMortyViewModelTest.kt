package com.example.rickandmorty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.hamcrest.core.Every
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.EmptyStackException
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex.Empty


/*
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class RickAndMortyViewModelTest {
    @RelaxedMockK
    lateinit var rickAndMortyApiService: RickAndMortyApiService
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun whenSuccesfulThenStateIsDone() = runTest {

        var listItem = listOf<ItemRickAndMorty>(
            ItemRickAndMorty("https://rickandmortyapi.com/api/character/avatar/1.jpeg", "Rick Sanchez"),
            ItemRickAndMorty("https://rickandmortyapi.com/api/character/avatar/2.jpeg\",\"episode\":[\"https://rickandmortyapi.com/api/episode/1\",\"https://rickandmortyapi.com/api/episode/2\",\"https://rickandmortyapi.com/api/e", "Morty Smith")
        )
       // coEvery { rickAndMortyApiService.getItems().results } returns listItem
        MockKAnnotations.init(this)
        println(rickAndMortyApiService)
       // rickAndMortyApiService.getItems().results = listItem
        val rickAndMortyViewModel = RickAndMortyViewModel(rickAndMortyApiService)
        rickAndMortyViewModel.getCharacteres()
        assertEquals(rickAndMortyViewModel.status.value, RickAndMortyStatus.DONE)
    }
}
*/

@OptIn(ExperimentalCoroutinesApi::class)
class RickAndMortyViewModelTest {
    //@ExperimentalCoroutinesApi
    //Lo q vas a mokear, es decir, crear tu con tus datos. en el ViewModel @RelaxedMockK
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
