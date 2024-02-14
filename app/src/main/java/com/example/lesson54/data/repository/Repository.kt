package com.example.lesson54.data.repository

import com.example.lesson54.data.model.CharactersResponse
import com.example.lesson54.data.model.ResultsItem
import com.example.lesson54.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {

    private val apiService = RetrofitClient.rickAndMortyApiService
    fun getResultsItems(
        onResponse: (resultsItems: List<ResultsItem>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        apiService.getCharacters().enqueue(object : Callback<CharactersResponse> {
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        onResponse(it.results!!)
                    }
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}