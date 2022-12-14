package com.example.toffeelite.ui.home

import androidx.lifecycle.*
import com.example.toffeelite.constants.Constants
import com.example.toffeelite.data.model.Event
import com.example.toffeelite.data.model.GoToMovie
import com.example.toffeelite.data.model.MovieListType
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.data.repository.MovieRepository
import com.example.toffeelite.util.extension.appendList
import com.example.toffeelite.util.extension.liveDataBlockScope

class HomeViewModel : ViewModel(), GoToMovie{

    private val movieRepository = MovieRepository()

    private val loadedFavouriteMovieList: LiveData<List<Search>>
    private val favouritePage = MutableLiveData<Int>().apply { value = 1 }
    val favouriteMovieList = MediatorLiveData<MutableList<Search>>()

    private val _goToShowAllEvent = MutableLiveData<Event<MovieListType>>()
    val goToShowAllEvent: LiveData<Event<MovieListType>> = _goToShowAllEvent

    override val goToMovieDetailsEvent: MutableLiveData<Event<Search>> = MutableLiveData()

    private val loadedLatestMovieList: LiveData<List<Search>>
    private val latestPage = MutableLiveData<Int>().apply { value = 1 }
    val latestMovieList = MediatorLiveData<MutableList<Search>>()


    init {
        loadedFavouriteMovieList = favouritePage.switchMap {
            liveDataBlockScope {
                movieRepository.loadFavouriteMovieList(Constants.FavouriteMovieTitle,it) {
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


        loadedLatestMovieList = latestPage.switchMap {
            liveDataBlockScope {
                movieRepository.loadLatestMovieList("man",it, 2022) {
                    println("iterror $it")
                }
            }
        }


        latestMovieList.addSource(loadedLatestMovieList) {
            it?.let { list ->
                println("it latest $it")
                println("list latest $list")
                latestMovieList.appendList(list)
            }
        }
    }


    fun loadMoreFavourites() {
        favouritePage.value = favouritePage.value?.plus(1)
    }

    fun loadMoreLatest() {
        latestPage.value = latestPage.value?.plus(1)
    }

    fun goToShowAllPressed(movieListType: MovieListType) {
        _goToShowAllEvent.value = Event(movieListType)
        println("goToShowAllEvent ${_goToShowAllEvent.value}")
    }
}