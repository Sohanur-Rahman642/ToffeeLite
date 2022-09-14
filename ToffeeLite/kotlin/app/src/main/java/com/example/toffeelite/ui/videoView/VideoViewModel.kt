package com.example.toffeelite.ui.videoView

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.toffeelite.data.model.omdb.entity.Movie
import com.example.toffeelite.data.repository.MovieRepository
import com.example.toffeelite.ui.movieDetails.MovieDetailsViewModel
import com.example.toffeelite.util.extension.liveDataBlockScope

class VideoViewModelFactory(private val playBackUrl: String, private val imdbId: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(playBackUrl, imdbId) as T
    }
}

class VideoViewModel(playBackUrl: String, imdbId: String) : ViewModel() {

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