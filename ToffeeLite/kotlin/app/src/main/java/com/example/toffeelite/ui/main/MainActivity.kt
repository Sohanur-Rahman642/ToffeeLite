package com.example.toffeelite.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.toffeelite.R
import com.example.toffeelite.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.showAllFragment,
                R.id.movieDetailsFragment,
                R.id.videoViewFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}