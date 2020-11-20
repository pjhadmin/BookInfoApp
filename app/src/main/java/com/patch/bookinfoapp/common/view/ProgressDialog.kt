package com.patch.bookinfoapp.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.patch.bookinfoapp.R

class ProgressDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_progress)
        initDialog()
    }

    private fun initDialog() {
        window?.attributes = WindowManager.LayoutParams().apply {
            copyFrom(window?.attributes)
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
        }
    }

    override fun show() {
        if (!isShowing) {
            super.show()
        }
    }
}