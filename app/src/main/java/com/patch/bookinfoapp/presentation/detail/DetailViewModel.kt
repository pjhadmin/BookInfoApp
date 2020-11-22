package com.patch.bookinfoapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patch.bookinfoapp.domain.entity.BookEntity

class DetailViewModel: ViewModel(){

    private val _book = MutableLiveData<BookEntity.Book>()
    val book: LiveData<BookEntity.Book> get() = _book

    fun setBookDetailData(book: BookEntity.Book?) {
        book?.let { _book.value = it }
    }
}