package com.patch.bookinfoapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patch.bookinfoapp.domain.entity.BookEntity

class DetailViewModel: ViewModel(){
    //SavedStateHandle로 값을 대신 전달해보기
    private val _book = MutableLiveData<BookEntity.Book>()
    val book: LiveData<BookEntity.Book> get() = _book

    fun setBookDetailData(book: BookEntity.Book?) {
        book?.let { _book.value = it }
    }
}