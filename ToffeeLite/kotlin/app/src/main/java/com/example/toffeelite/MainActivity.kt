package com.example.toffeelite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.toffeelite.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.navigation_home -> navView.visibility = View.VISIBLE
//                R.id.navigation_tv_shows -> navView.visibility = View.VISIBLE
//                R.id.navigation_movies -> navView.visibility = View.VISIBLE
//                else -> navView.visibility = View.GONE
//            }
//        }
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.showAllFragment, R.id.movieDetailsFragment, R.id.videoViewFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}