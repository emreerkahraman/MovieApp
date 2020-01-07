package com.example.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

@BindingAdapter("posterPath")
fun ImageView.posterPath(url: String?) {

     val path = "https://image.tmdb.org/t/p/w342/$url"

    Picasso.get().load(path).transform(RoundedCornersTransformation(20,0)).into(this)

}


@BindingAdapter("backDrop")
fun ImageView.backDrop(url: String?) {

    val path = "https://image.tmdb.org/t/p/w500/$url"

    Picasso.get().load(path).into(this)

}