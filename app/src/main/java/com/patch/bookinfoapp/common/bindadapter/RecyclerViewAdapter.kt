package com.patch.bookinfoapp.common.bindadapter

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.presentation.main.BookAdapter

@BindingAdapter("submitBookList")
fun RecyclerView.setBookList(
    items: PagedList<BookEntity.Book>?
) {
    items?.let{
        (adapter as? BookAdapter)?.run {
            submitList(it)
        }
    }
}