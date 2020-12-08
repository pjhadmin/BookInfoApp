package com.patch.bookinfoapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patch.bookinfoapp.R
import com.patch.bookinfoapp.presentation.main.SearchMainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchMainFragment.newInstance())
                .commitNow()
        }
    }
}