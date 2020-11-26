package com.example.marvelApp.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelApp.R
import com.example.marvelApp.data.model.Thumbnail
import com.example.marvelApp.utils.Constants.Companion.IMAGE_TYPE
import com.example.marvelApp.utils.Constants.Companion.IMAGE_TYPE_FULL

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, thumbnail: Thumbnail?) {
    thumbnail?.let {
        val imgUrl = thumbnail.path + IMAGE_TYPE + thumbnail.extension
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("imageUrlFull")
fun bindImageFull(imgView: ImageView, thumbnail: Thumbnail?) {
    thumbnail?.let {
        val imgUrl = thumbnail.path + IMAGE_TYPE_FULL + thumbnail.extension
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}