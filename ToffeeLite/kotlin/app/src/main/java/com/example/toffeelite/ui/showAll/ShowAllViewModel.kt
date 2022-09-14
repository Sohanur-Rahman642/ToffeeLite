package com.example.toffeelite.ui.showAll

import androidx.lifecycle.*
import com.example.toffeelite.constants.Constants
import com.example.toffeelite.data.model.Event
import com.example.toffeelite.data.model.GoToMovie
import com.example.toffeelite.data.model.MovieListType
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.data.repository.MovieRepository
import com.example.toffeelite.util.extension.appendList
import com.example.toffeelite.util.extension.liveDataBlockScope


class ShowAllViewModelFactory(private val movieListType: MovieListType) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowAllViewModel(movieListType) as T
    }
}

class ShowAllViewModel(movieListType: MovieListType) : ViewModel(), GoToMovie {

    private val movieRepository = MovieRepository()
    private val moviePage = MutableLiveData<Int>().apply { value = 1 }
    private val loadedMovieList: LiveData<List<Search>>
    val movieList = MediatorLiveData<MutableList<Search>>()

    override val goToMovieDetailsEvent: MutableLiveData<Event<Search>> = MutableLiveData()

    init {
        loadedMovieList = when (movieListType) {
            MovieListType.Favourite -> {
                liveDataBlockScope {
                    moviePage.switchMap {
                        liveDataBlockScope {
                            movieRepository.loadFavouriteMovieList(Constants.FavouriteMovieTitle,it) {
                                println("iterror $it")
                            }
                        }
                    }
                }
            }
            else -> {
                moviePage.switchMap {
                    liveDataBlockScope {
                        movieRepository.loadLatestMovieList("man",it, 2022) {
                            println("iterror $it")
                        }
                    }
                }
            }
        }

        movieList.addSource(loadedMovieList) { it?.let { list -> movieList.appendList(list) } }
    }

    fun loadMoreMovies() {
        moviePage.value = moviePage.value?.plus(1)
    }
}