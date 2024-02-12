package com.example.lesson54.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson54.data.model.ResultsItem
import com.example.lesson54.data.repository.Repository
import com.example.lesson54.utils.UiState

class MainViewModel : ViewModel() {

    var charactersList = listOf<ResultsItem>()
    private val repository = Repository()
    private val _uiState = MutableLiveData<UiState<List<ResultsItem>>>()
    val uistate: LiveData<UiState<List<ResultsItem>>> = _uiState
    fun getCharacters() {
        repository.getResultsItems(
            onResponse = { resultItem ->
                _uiState.value = UiState(success = resultItem, isLoading = false)
            },
            onFailure = { error ->
                _uiState.value = UiState(errorMessage = error.message, isLoading = false)
            }
        )
    }
}