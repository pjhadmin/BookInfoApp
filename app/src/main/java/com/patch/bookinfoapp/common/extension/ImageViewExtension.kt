package com.patch.bookinfoapp.common.extension

import androidx.databinding.BindingAdapter
import com.patch.bookinfoapp.common.view.BookImageView

@BindingAdapter("url")
fun BookImageView.loadImage(
    url: String?
) {
    if (!url.isNullOrEmpty()) {
        this.load(url)
    }
}