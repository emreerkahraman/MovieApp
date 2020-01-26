package com.example.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.movieapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


@BindingAdapter("posterPath")
fun ImageView.posterPath(url: String?) {


     val path = "https://image.tmdb.org/t/p/w342/$url"


    Picasso.get().load(path)
        .resize(342,513)
        .error(R.drawable.error342)
        .transform(RoundedCornersTransformation(20,0))
        .into(this)


}


@BindingAdapter("backDrop")
fun ImageView.backDrop(url: String?) {

    val path = "https://image.tmdb.org/t/p/w500/$url"

    Picasso.get().load(path)
        .resize(500,281)
        .error(R.drawable.error500)
        .into(this)

}