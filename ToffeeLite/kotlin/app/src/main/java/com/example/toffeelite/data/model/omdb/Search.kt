package com.example.toffeelite.data.model.omdb

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Poster")
    val Poster: String,
    @SerializedName("Title")
    val Title: String,
    @SerializedName("Type")
    val Type: String,
    @SerializedName("Year")
    val Year: String,
    @SerializedName("imdbID")
    val imdbID: String
)
