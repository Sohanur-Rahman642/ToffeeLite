package com.example.toffeelite.ui.showAll

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
import com.example.toffeelite.databinding.FragmentShowAllBinding
import com.example.toffeelite.ui.base.BaseFragment
import com.example.toffeelite.ui.home.HomeFragmentDirections

class ShowAllFragment : BaseFragment(true) {

    private val args: ShowAllFragmentArgs by navArgs()
    private val viewModel: ShowAllViewModel by viewModels { ShowAllViewModelFactory(args.movieListTypeArg) }
    private lateinit var viewDataBinding: FragmentShowAllBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentShowAllBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
                lifecycleOwner = this@ShowAllFragment.viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.goToMovieDetailsEvent.observe(
            viewLifecycleOwner,
            EventObserver { navigateToMovieDetails(it.imdbID, it.Title) })
    }

    private fun navigateToMovieDetails(imdbId: String, title: String) {
        val action =
            ShowAllFragmentDirections.actionShowAllFragmentToMovieDetailsFragment2(imdbId, title)
        findNavController().navigate(action)
    }

}