package com.patch.bookinfoapp.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
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
}