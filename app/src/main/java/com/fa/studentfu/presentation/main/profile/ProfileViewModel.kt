package com.fa.studentfu.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.domain.usecase.GetProfileDataUseCase
import com.fa.studentfu.domain.usecase.LogoutUserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val logoutUserUseCase: LogoutUserUseCase,
    private val getProfileDataUseCase: GetProfileDataUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState>
        get() = _state

    fun fetchUser(){
        viewModelScope.launch {
            getProfileDataUseCase().collect {
                _state.postValue(UiState.ResultAvailable(it))
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            logoutUserUseCase()
        }
        _state.postValue(UiState.LogOut)
    }

    sealed class UiState {
        object LogOut : UiState()
        object Loading : UiState()
        class ResultAvailable(val model : ProfileModel): UiState()
    }
}