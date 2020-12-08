package com.patch.bookinfoapp.common.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.patch.bookinfoapp.common.bindadapter.gone
import com.patch.bookinfoapp.common.bindadapter.visibleOrGone
import com.patch.bookinfoapp.common.util.hideKeyboard
import com.patch.bookinfoapp.common.util.showKeyboard
import com.patch.bookinfoapp.databinding.ViewSearchBinding

class SearchLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    val binding = ViewSearchBinding.inflate(LayoutInflater.from(context), this, true)
    private var listener: ((String?) -> Unit)? = null

    init {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(charSequence: Editable?) {}
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                if (charSequence.isNullOrEmpty()) {
                    binding.ivDeleteImg.gone()
                } else {
                    binding.ivDeleteImg.visibleOrGone(charSequence.isNotEmpty())
                    listener?.invoke(charSequence.toString())
                }
            }
        })

        binding.ivDeleteImg.setOnClickListener {
            binding.etSearch.text?.clear()
            setFocus(true)
        }
    }

    fun setOnTextChangedListener(listener: ((String?) -> Unit)?) {
        this.listener = listener
    }

    fun setFocus(needFocus: Boolean) {
        with(binding.etSearch) {
            if (needFocus) {
                requestFocus()
                showKeyboard(this)
            } else {
                clearFocus()
                hideKeyboard(this)
            }
        }
    }
}