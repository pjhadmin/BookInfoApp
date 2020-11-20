package com.patch.bookinfoapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookInfoApplication: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: BookInfoApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}