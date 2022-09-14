package com.example.toffeelite.data.model

import androidx.lifecycle.MutableLiveData
import com.example.toffeelite.data.model.omdb.Search


interface GoToMovie {
    val goToMovieDetailsEvent: MutableLiveData<Event<Search>>

    fun goTo(movie: Search) {
        goToMovieDetailsEvent.value = Event(movie)
    }
}
