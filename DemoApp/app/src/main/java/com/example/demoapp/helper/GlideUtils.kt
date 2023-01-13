package com.example.demoapp.helper

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.demoapp.R

@BindingAdapter("android:src")
fun setImageViewResource(imageView: AppCompatImageView, resource: String?) {
    resource?.let {
        Glide.with(imageView.context).load(it).placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    } ?: imageView.setImageResource(R.drawable.ic_launcher_background)
}