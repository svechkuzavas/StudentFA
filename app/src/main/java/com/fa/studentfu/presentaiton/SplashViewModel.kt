package com.fa.studentfu.presentaiton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val _status = MutableLiveData<SplashUiState>(SplashUiState.INITIAL)
    val status : LiveData<SplashUiState> = _status


}