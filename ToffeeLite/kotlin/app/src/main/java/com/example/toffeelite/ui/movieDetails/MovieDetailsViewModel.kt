package com.example.toffeelite.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.toffeelite.data.model.Event
import com.example.toffeelite.data.model.GoToMovie
import com.example.toffeelite.data.model.GoToVideoView
import com.example.toffeelite.data.model.MovieListType
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.data.model.omdb.entity.Movie
import com.example.toffeelite.data.repository.MovieRepository
import com.example.toffeelite.util.extension.liveDataBlockScope

//Custom ViewModel Factory with imdbId argument
class MovieDetailsViewModelFactory(private val imdbId: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(imdbId) as T
    }
}

class MovieDetailsViewModel(imdbId: String) : ViewModel(), GoToVideoView{

    private val movieRepository = MovieRepository()
    val movie: LiveData<Movie>

    private val _gotoVideoView = MutableLiveData<Event<Movie>>()
    val gotoVideoView: LiveData<Event<Movie>> = _gotoVideoView

    override val goToVideoViewEvent: MutableLiveData<Event<Movie>> = MutableLiveData()

    init {
        movie = liveDataBlockScope {
            movieRepository.loadDetails(imdbId) {
                println("movie in viewmodel $it")
            }
        }

    }

    fun click(movie: Movie){
        println("clicked")
        _gotoVideoView.value = Event(movie)
    }



}