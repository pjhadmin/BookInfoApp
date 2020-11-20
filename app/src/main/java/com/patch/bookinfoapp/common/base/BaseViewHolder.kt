package com.patch.bookinfoapp.common.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.patch.bookinfoapp.BR

open class BaseViewHolder<T : Any?>(val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {
    open fun onBind(item: T?) {
        try {
            with(binding) {
                setVariable(BR.item, item)
                executePendingBindings()
            }

        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}