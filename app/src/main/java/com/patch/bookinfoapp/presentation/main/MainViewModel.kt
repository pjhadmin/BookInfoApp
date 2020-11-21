package com.patch.bookinfoapp.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.patch.bookinfoapp.common.base.BaseViewModel
import com.patch.bookinfoapp.domain.usecase.BookSearchUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val bookSearchUseCase: BookSearchUseCase
): BaseViewModel(){
    var bookLiveData = bookSearchUseCase.initSearchBookListLiveData(viewModelScope)

    fun searchBookKeyword(query: String?) {
        query?.let {
            viewModelScope.launch {
                delay(300)
                if (it.isNotEmpty()) { bookSearchUseCase.sendQueryString(it) }
            }
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