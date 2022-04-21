package com.fa.studentfu.presentation.sign.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.domain.usecase.LoginUserUseCase
import com.fa.studentfu.domain.usecase.SaveTokenUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel(){

    private val loginData = LoginData("", "", "")
    private val stateData : MutableLiveData<UiState> = MutableLiveData(UiState.Input(loginData))
    val state : LiveData<UiState> = stateData

    fun setUsername(value : String){
        loginData.username = value
    }

    fun setPassword(value : String){
        loginData.password = value
    }

    fun login(){
        stateData.postValue(UiState.Loading)
        viewModelScope.launch {
            loginUserUseCase(AuthorizationModel.UserCredentials(loginData.username, loginData.password))
                .collect {
                    result ->
                    when (result){
                        is BaseResult.Success -> {
                            loginData.token = result.data.token
                            stateData.postValue(
                                UiState.ResultAvailable(LoginResult.Success(result.data.token))
                            )
                        }
                        is BaseResult.Error -> {
                            stateData.postValue(
                                UiState.ResultAvailable(LoginResult.Error(result.err.message))
                            )
                        }
                    }
                }
        }
    }

    fun saveToken(){
        viewModelScope.launch {
            saveTokenUseCase(loginData.token)
        }
    }

    sealed class UiState{
        class Input(val loginData: LoginData) : UiState()
        object Loading : UiState()
        class ResultAvailable(val result: LoginResult) : UiState()
    }

    sealed class LoginResult {
        class Success(val token: String) : LoginResult()
        class Error(val message: String) : LoginResult()
    }


    data class LoginData(
        var username : String,
        var password : String,
        var token : String
    )
}