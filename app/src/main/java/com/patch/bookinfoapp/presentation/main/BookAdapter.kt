package com.patch.bookinfoapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.patch.bookinfoapp.R
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.patch.bookinfoapp.common.base.BaseViewHolder
import com.patch.bookinfoapp.databinding.ItemBookMainBinding
import com.patch.bookinfoapp.domain.entity.BookEntity

class BookAdapter(private val onClick: (BookEntity.Book) -> Unit): PagedListAdapter<BookEntity.Book, BookAdapter.BookViewHolder>(
    diffCallback
) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BookEntity.Book>() {
            override fun areItemsTheSame(
                oldItem: BookEntity.Book,
                newItem: BookEntity.Book
            ): Boolean = oldItem.isbn == newItem.isbn

            override fun areContentsTheSame(
                oldItem: BookEntity.Book,
                newItem: BookEntity.Book
            ): Boolean =
                oldItem.isbn == newItem.isbn
                        && oldItem.contents == newItem.contents
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            DataBindingUtil.inflate<ItemBookMainBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_book_main,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.binding.root.setOnClickListener{
            getItem(position)?.let{
                onClick(it)
            }
        }
    }

    class BookViewHolder(binding: ItemBookMainBinding): BaseViewHolder<BookEntity.Book>(binding)
}