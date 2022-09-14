package com.example.toffeelite.ui.binding

import android.view.View
import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("bind_rating_bar", "bind_rating_stars", "bind_rating_bar_view")
fun RatingBar.bindRatingBar(rating: String?, stars: Int, ratingBar: RatingBar) {
    try {
        rating?.let {
            if (rating != null) {
                this.rating = stars * rating.toFloat() / 10f
            }
        }
    }catch (e: Exception){
        ratingBar.visibility = View.GONE
    }
}