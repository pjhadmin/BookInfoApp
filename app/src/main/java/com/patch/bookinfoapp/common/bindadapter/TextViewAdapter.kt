package com.patch.bookinfoapp.common.bindadapter

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.patch.bookinfoapp.common.util.formatToServerDateDefaults
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("commaText")
fun AppCompatTextView.setCommaText(authors: List<String>?) {
    authors?.let {
        var authorsStr = ""
        for(author in it) {
            if (authorsStr.isEmpty())
                authorsStr = author
            else
                authorsStr += ",$author"
        }
        isVisible = authorsStr.isNotEmpty()
        text = authorsStr
    } ?: let {
        isVisible = false
    }
}

@BindingAdapter("visibilityText")
fun AppCompatTextView.setVisibilityText(input: String?) {
    input?.let {
        text = it
        isVisible = it.isNotEmpty()
    } ?: let {
        isVisible = false
    }
}

@BindingAdapter("dateTimeText")
fun AppCompatTextView.setDateTimeText(timeStr: String?) {
    timeStr?.let {
        if (it.isNotEmpty()) {
            try{
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse(it)
                text = date.formatToServerDateDefaults()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}