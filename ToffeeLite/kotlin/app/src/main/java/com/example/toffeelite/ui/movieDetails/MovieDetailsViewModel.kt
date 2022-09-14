package com.example.toffeelite.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class MovieDetailsViewModel(imdbId: String) : ViewModel() {

    private val movieRepository = MovieRepository()
    val movie: LiveData<Movie>


    init {
        movie = liveDataBlockScope {
            movieRepository.loadDetails(imdbId) {
                println("movie in viewmodel $it")
            }
        }

    }

}