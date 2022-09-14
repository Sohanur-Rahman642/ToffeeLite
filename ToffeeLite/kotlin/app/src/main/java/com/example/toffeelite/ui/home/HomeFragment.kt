package com.example.toffeelite.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.toffeelite.R
import com.example.toffeelite.databinding.FragmentHomeBinding
import com.example.toffeelite.ui.base.BaseFragment

class HomeFragment : BaseFragment(false)  {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_home, container, false)
        viewDataBinding.viewmodel = viewModel
        viewDataBinding.lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        return viewDataBinding.root
    }


    override fun setupViewModelObservers() {

    }

}