package com.example.toffeelite.data.remote

import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.data.model.omdb.entity.Movie
import com.example.toffeelite.data.model.response.OmdbMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    interface  MovieService{
        @GET("?")
        fun fetchFavouriteList(@Query("s") title : String, @Query("page") page : Int) : Call<OmdbMovieResponse>

        @GET("?")
        fun fetchLatestList(@Query("s") title : String, @Query("page") page : Int, @Query("y") year : Int) : Call<OmdbMovieResponse>

        @GET("?")
        fun fetchDetails(@Query("i") id : String) : Call<Movie>
    }
}