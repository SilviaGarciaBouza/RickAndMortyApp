package com.example.rickandmorty
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope



enum class RickAndMortyStatus {LOADING, ERROR, DONE}

class RickAndMortyViewModel: ViewModel() {

        private val _status = MutableLiveData<RickAndMortyStatus>()
        val status: LiveData<RickAndMortyStatus> = _status

        private val _characteres = MutableLiveData<List<ItemRickAndMorty>>()
        val characteres: LiveData<List<ItemRickAndMorty>> = _characteres



        init {
            getCharacteres()
        }

        private fun getCharacteres() {
            viewModelScope.launch {
                _status.value = RickAndMortyStatus.LOADING
                try {
                    _characteres.value = rickAndMortyService1.getItems().results
                    _status.value = RickAndMortyStatus.DONE
                    Log.d("vieModel","Número de valores: ${-characteres.value?.count()!!}")
                } catch (e: Exception) {
                    _status.value = RickAndMortyStatus.ERROR
                   // _characteres.value = listOf()
                }
            }
        }
}



