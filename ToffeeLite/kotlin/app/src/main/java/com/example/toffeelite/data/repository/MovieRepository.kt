package com.example.toffeelite.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.data.remote.ApiInterface
import com.example.toffeelite.util.ServiceBuilder

class MovieRepository : BaseRepository() {
    private val movieService =
        ServiceBuilder.buildService(ApiInterface.MovieService::class.java);

    //coroutine higher order function to fetch favourite movie list such as Batman
    //Takes 3 parameters: a title, a page number, an errortext function with a string param
    suspend fun loadFavouriteMovieList(title:String, page: Int, errorText: (String) -> Unit) =
        loadPageListCall(
            { movieService.fetchFavouriteList(title, page) },
            MutableLiveData<List<Search>>(),
            errorText
        )


    //coroutine higher order function to fetch latest movie list with year = 2022 filter
    //Takes 4 parameters: a title, a page number, year, an errortext function with a string param
    suspend fun loadLatestMovieList(title:String, page: Int, year: Int, errorText: (String) -> Unit) =
        loadPageListCall(
            { movieService.fetchLatestList(title, page, year) },
            MutableLiveData<List<Search>>(),
            errorText
        )
}