package com.example.toffeelite.data.remote

import com.example.toffeelite.data.model.response.OmdbMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    interface  MovieServicen{
        @GET("?")
        fun fetchFavouriteList(@Query("s") title : String, @Query("page") page : Int) : Call<OmdbMovieResponse>
    }
}