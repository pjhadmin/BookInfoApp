package com.patch.bookinfoapp.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.presentation.detail.DetailFragment.Companion.DETAIL_BOOK_ITEM
import com.patch.bookinfoapp.presentation.detail.DetailFragment.Companion.DETAIL_ITEM_INDEX

class DetailViewModel constructor(
    private val savedStateHandle: SavedStateHandle) : ViewModel(){

    val book: MutableLiveData<BookEntity.Book> by lazy {
        MutableLiveData<BookEntity.Book>(savedStateHandle[DETAIL_BOOK_ITEM])
    }

    val bookIndex: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(savedStateHandle[DETAIL_ITEM_INDEX])
    }
}