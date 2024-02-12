package com.example.lesson54.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val results: List<ResultsItem>?,
    @SerializedName("info") val info: Info
)