package com.patch.bookinfoapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var bookLikeIndex: MutableLiveData<Int> = MutableLiveData(-1)
}