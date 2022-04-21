package com.fa.studentfu.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.domain.usecase.LogoutUserUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(private val logoutUserUseCase: LogoutUserUseCase) : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState>
        get() = _state

    fun logout(){
        viewModelScope.launch {
            logoutUserUseCase()
        }
        _state.postValue(UiState.LogOut)
    }

    sealed class UiState {
        object LogOut : UiState()
    }
}