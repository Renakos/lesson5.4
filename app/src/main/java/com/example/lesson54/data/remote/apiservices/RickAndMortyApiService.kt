package com.example.lesson54.data.remote.apiservices

import com.example.lesson54.data.model.Character
import retrofit2.Call
import retrofit2.http.GET
private const val  POSTS_END_POINT = "api/character"
interface RickAndMortyApiService {

    @GET(POSTS_END_POINT)
    fun getCharacters() : Call<List<Character>>

}