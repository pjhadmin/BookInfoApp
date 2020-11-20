package com.patch.bookinfoapp.common.extension

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patch.bookinfoapp.domain.entity.BookEntity

@BindingAdapter("submitBookList")
fun RecyclerView.setBookList(
    items: PagedList<BookEntity.Book>?
) {
    items?.let{
        (adapter as? PagedListAdapter<BookEntity.Book, *>)?.run {
            submitList(items)
        }
    }
}