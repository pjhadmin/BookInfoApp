package com.patch.bookinfoapp.common.bindadapter

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.patch.bookinfoapp.common.util.formatToServerDateDefaults
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("commaText")
fun setCommaText(view: AppCompatTextView, authors: List<String>?) {
    authors?.let {
        var authorsStr = ""
        for(author in it) {
            if (authorsStr.isEmpty())
                authorsStr = author
            else
                authorsStr += ",$author"
        }
        view.isVisible = authorsStr.isNotEmpty()
        view.text = authorsStr
    } ?: view.let {
        it.isVisible = false
    }
}

@BindingAdapter("visibilityText")
fun setVisibilityText(view: AppCompatTextView, input: String?) {
    input?.let {
        view.text = it
        view.isVisible = it.isNotEmpty()
    } ?: view.let {
        it.isVisible = false
    }
}

@BindingAdapter("dateTimeText")
fun setDateTimeText(view: AppCompatTextView, timeStr: String?) {
    timeStr?.let {
        if (it.isNotEmpty()) {
            try{
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse(it)
                view.text = date.formatToServerDateDefaults()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}