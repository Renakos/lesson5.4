package com.example.lesson54.data.repository

import com.example.lesson54.data.model.CharactersResponse
import com.example.lesson54.data.model.ResultsItem
import com.example.lesson54.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {

    private val apiService = RetrofitClient.rickAndMortyApiService

//    fun getResultsItems(callback: CustomCallback<List<ResultsItem>>) {
//        apiService.getResultsItems().enqueue(object : Callback<List<ResultsItem>> {
//            override fun onResponse(call: Call<List<ResultsItem>>, response: Response<List<ResultsItem>>) {
//                if (response.isSuccessful && response.body() != null) {
//                    callback.onResponse(response.body()!!)
//                }
//            }
//
//            override fun onFailure(call: Call<List<ResultsItem>>, t: Throwable) {
//                callback.onFailure(t)
//            }
//        })
//    }

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