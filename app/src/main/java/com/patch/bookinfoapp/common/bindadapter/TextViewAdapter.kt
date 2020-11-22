package com.patch.bookinfoapp.common.bindadapter

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.patch.bookinfoapp.common.util.formatToServerDateDefaults
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("commaText")
fun AppCompatTextView.setCommaText(authors: List<String>?) {

    if (authors.isNullOrEmpty()) {
        isVisible = false
    } else {
        var authorsStr = ""
        for(author in authors) {
            if (authorsStr.isEmpty())
                authorsStr = author
            else
                authorsStr += ",$author"
        }
        isVisible = authorsStr.isNotEmpty()
        text = authorsStr
    }
}

@BindingAdapter("visibilityText")
fun AppCompatTextView.setVisibilityText(input: String?) {
    if (input.isNullOrEmpty()) {
        isVisible = false
    } else {
        text = input
        isVisible = input.isNotEmpty()
    }
}

@BindingAdapter("dateTimeText")
fun AppCompatTextView.setDateTimeText(timeStr: String?) {
    if (timeStr.isNullOrEmpty()) {
        return
    } else {
        try{
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse(timeStr)
            text = date.formatToServerDateDefaults()
        } catch (e: Exception) { e.printStackTrace() }
    }
}