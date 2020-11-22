package com.patch.bookinfoapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.patch.bookinfoapp.R
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.patch.bookinfoapp.BR
import com.patch.bookinfoapp.databinding.ItemBookMainBinding
import com.patch.bookinfoapp.domain.entity.BookEntity

class BookAdapter(private val onClick: (BookEntity.Book, Int) -> Unit): PagedListAdapter<BookEntity.Book, BookAdapter.BookViewHolder>(
    diffCallback
) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BookEntity.Book>() {
            override fun areItemsTheSame(
                oldItem: BookEntity.Book,
                newItem: BookEntity.Book
            ): Boolean {
                return oldItem.isbn == newItem.isbn
            }

            override fun areContentsTheSame(
                oldItem: BookEntity.Book,
                newItem: BookEntity.Book
            ): Boolean {
                return oldItem.isbn == newItem.isbn
                        && oldItem.contents == newItem.contents
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_book_main,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        holder.binding.root.setOnClickListener{
            getItem(position)?.let{
                onClick(it, position)
            }
        }
    }

    class BookViewHolder(val binding: ItemBookMainBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BookEntity.Book?) {
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
}