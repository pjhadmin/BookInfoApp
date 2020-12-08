package com.patch.bookinfoapp.common.bindadapter

import android.view.View
import androidx.databinding.BindingAdapter

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

// Xml 에서 View를 입포트 하지 않아도 됨
@BindingAdapter("visibleOrGone")
fun View.visibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}