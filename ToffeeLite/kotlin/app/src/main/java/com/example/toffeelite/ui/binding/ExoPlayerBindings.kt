package com.example.toffeelite.ui.binding

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView

@BindingAdapter("bind_progress_bar")
fun ProgressBar.bindProgressBar(playerView: StyledPlayerView) {
    val exoPlayer = playerView.player
    if(exoPlayer != null){
        if(exoPlayer.isLoading){
            this.visibility = View.GONE
        }
    }

}