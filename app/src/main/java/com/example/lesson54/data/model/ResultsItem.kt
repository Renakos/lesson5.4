package com.example.lesson54.data.model

import com.google.gson.annotations.SerializedName

data class ResultsItem(
    @SerializedName("image")
    val image: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("species")
    val species: String = "",
    @SerializedName("created")
    val created: String = "",
    @SerializedName("name")
    val name: String? = null
)
