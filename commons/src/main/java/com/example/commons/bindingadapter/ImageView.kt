package com.example.commons.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadUrlImage")
fun ImageView.loadUrlImage(url: String?) {
    if (url == null) return

    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}