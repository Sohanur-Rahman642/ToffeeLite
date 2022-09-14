package com.example.toffeelite.data.model.response

import com.example.toffeelite.data.model.omdb.Search
import com.google.gson.annotations.SerializedName

data class OmdbMovieResponse(
    @SerializedName("Search")
    var search: List<Search>,

    @SerializedName("Response")
    var response: String,

    @SerializedName("totalResults")
    var totalResults: String,

    )
