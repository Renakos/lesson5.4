package com.example.lesson54.data.remote.apiservices

import com.example.lesson54.data.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET

private const val CHARACTERS_END_POINT = "api/character"

interface RickAndMortyApiService {

    @GET(CHARACTERS_END_POINT)
    fun getCharacters(): Call<CharactersResponse>

}