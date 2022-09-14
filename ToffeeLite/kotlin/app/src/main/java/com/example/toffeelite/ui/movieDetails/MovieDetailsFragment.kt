package com.example.toffeelite.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.toffeelite.R
import com.example.toffeelite.data.model.EventObserver
import com.example.toffeelite.databinding.FragmentMovieDetailsBinding
import com.example.toffeelite.ui.base.BaseFragment
import com.example.toffeelite.ui.home.HomeFragmentDirections

class MovieDetailsFragment : BaseFragment(true) {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels { MovieDetailsViewModelFactory(args.idmbIdArg) }
    private lateinit var viewDataBinding: FragmentMovieDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding =
            FragmentMovieDetailsBinding.inflate(inflater, container, false)
                .apply {
                    viewmodel = viewModel
                    lifecycleOwner = this@MovieDetailsFragment.viewLifecycleOwner
                }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.goToVideoView.observe(
            viewLifecycleOwner,
            EventObserver { navigateToVideoView() })
    }

    private fun navigateToVideoView() {
        val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToVideoViewFragment()
        findNavController().navigate(action)
    }
}