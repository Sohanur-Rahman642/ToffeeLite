package com.example.toffeelite.ui.videoView

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.toffeelite.R
import com.example.toffeelite.databinding.FragmentHomeBinding
import com.example.toffeelite.databinding.FragmentMovieDetailsBinding
import com.example.toffeelite.databinding.FragmentVideoViewBinding
import com.example.toffeelite.ui.base.BaseFragment
import com.example.toffeelite.ui.movieDetails.MovieDetailsFragmentArgs
import com.example.toffeelite.ui.movieDetails.MovieDetailsViewModel
import com.example.toffeelite.ui.movieDetails.MovieDetailsViewModelFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class VideoViewFragment : BaseFragment(true) {

    private val args: VideoViewFragmentArgs by navArgs()
    private val viewModel: VideoViewModel by viewModels { VideoViewModelFactory(args.playBackUrl, args.idmbIdArg) }
    private lateinit var viewDataBinding: FragmentVideoViewBinding

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding =
            FragmentVideoViewBinding.inflate(inflater, container, false)
                .apply {
                    viewmodel = viewModel
                    lifecycleOwner = this@VideoViewFragment.viewLifecycleOwner
                }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        preparePlayer()
    }


    private fun preparePlayer() {
        exoPlayer = activity?.let { ExoPlayer.Builder(it.baseContext).build() }
        exoPlayer?.playWhenReady = true
        viewDataBinding.playerView.player = exoPlayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(URL)
        val mediaSource =
            DashMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
        exoPlayer?.setMediaSource(mediaSource)
        exoPlayer?.seekTo(playbackPosition)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.prepare()
    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    companion object {
        const val URL =
            "https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd"
    }

}