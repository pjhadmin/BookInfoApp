package com.patch.bookinfoapp.presentation.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.patch.bookinfoapp.common.base.BaseViewModel
import com.patch.bookinfoapp.domain.usecase.BookSearchUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val bookSearchUseCase: BookSearchUseCase
): BaseViewModel(){
    var bookLiveData = bookSearchUseCase.initSearchBookListLiveData(viewModelScope)

    init {
        bookSearchUseCase.initQueryChannel().launchIn(viewModelScope)
    }

    fun sendQueryString(query: String) {
        if (query.isNotEmpty()) {
            bookSearchUseCase.sendQueryString(query)
        }
    }

    fun setLikeStatus(index: Int) {
        if (index < 0) return
        bookLiveData.value?.let {
            val item = it[index]
            item?.run {
                item.isLike = !isLike
            }
        }
    }
}