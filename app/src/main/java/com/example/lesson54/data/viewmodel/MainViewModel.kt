package com.example.lesson54.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson54.data.model.Character
import com.example.lesson54.data.repository.Repository
import com.example.lesson54.utils.UiState

class MainViewModel : ViewModel() {

    private val repository = Repository()

    private val _uiState = MutableLiveData<UiState<List<Character>>>()
    val uiState: LiveData<UiState<List<Character>>>
        get() = _uiState

    init {
        fetchData()
    }

    fun fetchData() {
        _uiState.postValue(UiState(isLoading = true))


        repository.getCharacters(
            onSuccess = { characters ->
                _uiState.value = UiState(success = characters)
            },
            onError = { error ->
                _uiState.value = UiState(errorMessage = error.message ?: "Unknown error")
            }
        )
    }
}
