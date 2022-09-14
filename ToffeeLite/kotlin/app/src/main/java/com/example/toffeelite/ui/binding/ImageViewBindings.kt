package com.example.toffeelite.ui.binding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.toffeelite.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.lang.Exception

@BindingAdapter("bind_poster_path")
fun ImageView.bindPosterImageWithPicasso(path: String?) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    Picasso.get().load(path).fit()
        .error(R.drawable.ic_baseline_image_24).into(this)
}


@BindingAdapter("bind_backdrop_path_loading", "bind_progress")
fun ImageView.bindBackdropImageWithPicassoLoading(path: String?, progressBar: ProgressBar) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    progressBar.visibility = View.VISIBLE
    Picasso.get().load(path).fit()
        .transform(RoundedCornersTransformation(4, 1))
        .error(R.drawable.ic_baseline_image_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                progressBar.visibility = View.GONE
            }
        })
}


