package com.example.toffeelite.data.model.response

import com.example.toffeelite.data.model.omdb.Search
import com.google.gson.annotations.SerializedName

data class OmdbMovieResponse(
    @SerializedName("Search")
    override var search: List<Search>,

    @SerializedName("Response")
    override var response: String,

    @SerializedName("totalResults")
    override var totalResults: String,

    ) : OmdbPageListResponse<Search>
