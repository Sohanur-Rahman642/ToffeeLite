package com.example.toffeelite.ui.home

import androidx.lifecycle.*
import com.example.toffeelite.constants.Constants
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.data.repository.MovieRepository
import com.example.toffeelite.util.extension.appendList
import com.example.toffeelite.util.extension.liveDataBlockScope

class HomeViewModel : ViewModel(){

    private val movieRepository = MovieRepository()

    private val loadedFavouriteMovieList: LiveData<List<Search>>
    private val favouritePage = MutableLiveData<Int>().apply { value = 1 }
    val favouriteMovieList = MediatorLiveData<MutableList<Search>>()


    init {
        loadedFavouriteMovieList = favouritePage.switchMap {
            liveDataBlockScope {
                println("run liveDataBlockScope 98765")
                movieRepository.loadFavouriteMovieList(Constants.FavouriteMovieTitle,it,) {
                    println("iterror $it")
                }
            }
        }

        favouriteMovieList.addSource(loadedFavouriteMovieList) {
            it?.let { list ->
                println("it $it")
                println("list $list")
                favouriteMovieList.appendList(list)
            }
        }
    }
}