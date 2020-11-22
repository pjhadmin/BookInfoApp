package com.patch.bookinfoapp.common.bindadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.patch.bookinfoapp.common.extension.toggleAnimation
import com.patch.bookinfoapp.common.view.BookImageView

@BindingAdapter("url")
fun BookImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        load(url)
    }
}

@BindingAdapter("isLike")
fun ImageView.isLike(isLike: Boolean) {
    isSelected = isLike
}

@BindingAdapter("toggleLike")
fun LottieAnimationView.toggleAnimation(isLike: Boolean) {
    toggleAnimation(isLike, true)
}