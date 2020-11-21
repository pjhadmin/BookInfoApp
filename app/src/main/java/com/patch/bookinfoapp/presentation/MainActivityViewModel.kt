package com.patch.bookinfoapp.presentation

import androidx.lifecycle.MutableLiveData
import com.patch.bookinfoapp.common.base.BaseViewModel

class MainActivityViewModel: BaseViewModel() {
    var bookLikeIndex: MutableLiveData<Int> = MutableLiveData(-1)
}