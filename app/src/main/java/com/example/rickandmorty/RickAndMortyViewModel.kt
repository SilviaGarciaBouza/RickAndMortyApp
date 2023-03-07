package com.example.rickandmorty
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope



enum class RickAndMortyStatus {LOADING, ERROR, DONE}

class RickAndMortyViewModel(private val rickAndMortyApiService: RickAndMortyApiService): ViewModel() {

        private val _status = MutableLiveData<RickAndMortyStatus>()
        val status: LiveData<RickAndMortyStatus> = _status

        private val _characteres = MutableLiveData<List<ItemRickAndMorty>>()
        val characteres: LiveData<List<ItemRickAndMorty>> = _characteres



        init {
            getCharacteres()
        }

        fun getCharacteres() {
            viewModelScope.launch {
                _status.value = RickAndMortyStatus.LOADING
                try {
                    _characteres.value = rickAndMortyApiService.getItems().results
                    _status.value = RickAndMortyStatus.DONE
                } catch (e: Exception) {
                    _status.value = RickAndMortyStatus.ERROR
                   _characteres.value = emptyList()
                }
            }
        }
}



