package com.example.toffeelite.data.model

import androidx.lifecycle.MutableLiveData
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.data.model.omdb.entity.Movie


interface GoToMovie {
    val goToMovieDetailsEvent: MutableLiveData<Event<Search>>

    fun goTo(movie: Search) {
        println("lollolol")
        goToMovieDetailsEvent.value = Event(movie)
    }

}

interface GoToVideoView {
    val goToVideoViewEvent: MutableLiveData<Event<Movie>>

    fun goToVideoView(movie: Movie){
        println("lol987654e34")
        goToVideoViewEvent.value = Event(movie)
    }
}
