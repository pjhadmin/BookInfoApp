package com.patch.bookinfoapp.common.bindadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.patch.bookinfoapp.common.extension.toggleAnimation
import com.patch.bookinfoapp.common.view.BookImageView

@BindingAdapter("url")
fun loadImage(view: BookImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.load(url)
    }
}

@BindingAdapter("isLike")
fun isLike(view: ImageView, isLike: Boolean) {
    view.isSelected = isLike
}

@BindingAdapter("toggleLike")
fun toggleAnimation(view: LottieAnimationView, isLike: Boolean) {
    view.toggleAnimation(isLike, true)
}