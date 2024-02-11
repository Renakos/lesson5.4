package com.example.lesson54.data.repository

import com.example.lesson54.data.model.Character
import com.example.lesson54.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService = RetrofitClient.rickAndMortyApiService

    fun getCharacters(onSuccess: (List<Character>) -> Unit, onError: (Throwable) -> Unit) {
        apiService.getCharacters().enqueue(object : Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                onError(t)
            }
        })
    }
}
