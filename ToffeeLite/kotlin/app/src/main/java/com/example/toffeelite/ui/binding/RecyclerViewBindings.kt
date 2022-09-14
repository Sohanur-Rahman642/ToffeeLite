package com.example.toffeelite.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.toffeelite.data.model.GoToMovie
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.ui.adapter.MovieListAdapter
import com.example.toffeelite.util.listener.InfiniteContentScrollListener


@BindingAdapter("bind_visibility_null_list")
fun View.bindViewVisibilityNullList(items: List<Any>?) {
    if (items == null) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


@BindingAdapter("bind_movie_list", "bind_load_more", "bind_view_model")
fun RecyclerView.bindMovieList(
    items: List<Search>?,
    loadMoreContent: () -> Unit,
    goTo: GoToMovie
) {
    if (items == null) return
    if (this.adapter == null) {
        this.adapter =
            MovieListAdapter(goTo, InfiniteContentScrollListener(this) { loadMoreContent() })
    }
    (this.adapter as MovieListAdapter).submitList(items)
}
