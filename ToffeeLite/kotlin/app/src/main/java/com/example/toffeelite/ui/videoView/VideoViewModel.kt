package com.example.toffeelite.ui.videoView

import android.content.Context
import android.text.BoringLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.toffeelite.data.model.omdb.entity.Movie
import com.example.toffeelite.data.repository.MovieRepository
import com.example.toffeelite.data.repository.SharedPreferenceRepository
import com.example.toffeelite.ui.movieDetails.MovieDetailsViewModel
import com.example.toffeelite.util.extension.liveDataBlockScope
import com.google.android.exoplayer2.ui.StyledPlayerView

class VideoViewModelFactory(
    private val playBackUrl: String,
    private val imdbId: String,
    private val context: Context) :

    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(playBackUrl, imdbId, context) as T
    }
}

class VideoViewModel(playBackUrl: String, imdbId: String, context: Context) : ViewModel() {

    private val movieRepository = MovieRepository()
    private val sharedPreferenceRepository = SharedPreferenceRepository(context)
    val movie: LiveData<Movie>
    private var currentPlayBackPosition : Long;

    private var _hideProgressBar = MutableLiveData<Boolean>()
    val hideProgressBar: LiveData<Boolean> = _hideProgressBar

    init {
        movie = liveDataBlockScope {
            movieRepository.loadDetails(imdbId) {
                println("movie in viewmodel $it")
            }
        }

        currentPlayBackPosition = sharedPreferenceRepository.getCurrentPlayBackPosition()
    }

    fun getCurrentPlayBackPosition(): Long {
        return sharedPreferenceRepository.getCurrentPlayBackPosition()
    }

    fun setCurrentPlayBackPosition(currentPlayBackPosition: Long) {
        sharedPreferenceRepository.setCurrentPlayBackPosition(currentPlayBackPosition)
    }

}