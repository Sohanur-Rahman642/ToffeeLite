package com.example.toffeelite.data.model

enum class MovieListType {
    Favourite {
        override fun toString() = "Favourite"
    },
    Latest {
        override fun toString() = "Latest"
    }
}
