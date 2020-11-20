package com.patch.bookinfoapp.common.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun showKeyboard(view: View?) {
    view?.let {
        val inputManager =
            it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun hideKeyboard(view: View?) {
    view?.let {
        val inputManager =
            it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        it.clearFocus()
    }
}