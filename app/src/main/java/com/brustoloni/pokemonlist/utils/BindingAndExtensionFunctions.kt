package com.brustoloni.pokemonlist.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.brustoloni.pokemonlist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?, placeHolder: Int = R.drawable.placeholder) {
    val context = imageView.context

    //load image
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    when (url.isNullOrBlank()) {
        true -> imageView.setImageResource(placeHolder)
        else -> {
            Glide.with(context)
                .load(url)
                .apply(
                    RequestOptions()
                        .error(R.drawable.placeholder)
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}

@BindingAdapter("android:src")
fun setSrcVector(view: ImageView, @DrawableRes drawable: Int) {
    view.setImageResource(drawable)
}

fun RecyclerView.loadMore(onLoadMore: () -> Unit){
    this.addOnScrollListener(EndlessRecyclerOnScrollListener(onLoadMore))
}