package com.patch.bookinfoapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.patch.bookinfoapp.common.base.BaseViewModel
import com.patch.bookinfoapp.domain.entity.BookEntity

class DetailViewModel: BaseViewModel(){

    private val _book = MutableLiveData<BookEntity.Book>()
    val book: LiveData<BookEntity.Book> get() = _book

    fun setBookDetailData(book: BookEntity.Book?) {
        book?.let { _book.value = it }
    }
}