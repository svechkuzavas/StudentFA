package com.fa.studentfu.presentation.sign.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.domain.usecase.CheckUserAuthorizedUseCase
import com.fa.studentfu.presentation.main.profile.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkUserAuthorizedUseCase: CheckUserAuthorizedUseCase
) : ViewModel() {

    private val stateData = MutableLiveData<UiState>(UiState.Loading)
    val state: LiveData<UiState> = stateData

    fun preloadApplication(){
        viewModelScope.launch {
            delay(500)
            checkUserAuthorizedUseCase().collect {
                isAuthorized ->
                stateData.postValue(UiState.ResultAvailable(isAuthorized))
            }
        }
    }

    sealed class UiState{
        object Initial : UiState()
        object Loading : UiState()
        class ResultAvailable(val isAuthorizeed : Boolean) : UiState()
    }

}