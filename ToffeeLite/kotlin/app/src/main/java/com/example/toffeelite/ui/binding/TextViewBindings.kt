package com.example.toffeelite.ui.binding

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.ExoPlayer

@BindingAdapter("bind_genres_text")
fun TextView.bindGenresText(genres: String?) {
    if (genres == null) return

    try {
        this.text = genres.replace(',','/')
    }catch (e: Exception){
        this.text = genres
    }
}

@BindingAdapter("bind_runtime_text")
fun TextView.bindMovieRuntime(runtimeInMinutes: String?) {
    if(runtimeInMinutes == null) return

    try {
        val updatedRuntimeMin = runtimeInMinutes.substringBefore(" ")
        val numberRuntimeMin: Float = updatedRuntimeMin.toFloat();
        val hoursText: String = (numberRuntimeMin / 60f).toString()
        val minutesText: String = (numberRuntimeMin % 60f).toString()
        val text = "${hoursText.substringBefore(".")} hours ${minutesText.substringBefore(".")} min"
        this.text = text
    }catch (e: Exception){
        this.text = runtimeInMinutes
    }
}


@BindingAdapter("bind_votes_text","bind_votes_text_view")
fun TextView.bindVotesText(votes: String?, view: TextView) {
    if (votes == null) return

    try {
        if(votes == "N/A"){
            view.visibility = View.GONE
        }else{
            this.text = "$votes votes"
        }
    }catch (e: Exception){
        this.text = votes
    }
}
