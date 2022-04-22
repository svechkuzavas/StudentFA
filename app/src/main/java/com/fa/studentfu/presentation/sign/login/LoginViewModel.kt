package com.fa.studentfu.presentation.sign.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.domain.usecase.LoginUserUseCase
import com.fa.studentfu.domain.usecase.SaveUserDataUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel(){

    private val loginData = LoginData("", "", "", "", "")
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
                            loginData.profileId = result.data.profileId
                            loginData.userId = result.data.userId
                            stateData.postValue(
                                UiState.ResultAvailable(LoginResult.Success)
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

    fun saveUserData(){
        viewModelScope.launch {
            saveUserDataUseCase(loginData.token, loginData.profileId, loginData.userId)
        }
    }

    sealed class UiState{
        class Input(val loginData: LoginData) : UiState()
        object Loading : UiState()
        class ResultAvailable(val result: LoginResult) : UiState()
    }

    sealed class LoginResult {
        object Success : LoginResult()
        class Error(val message: String) : LoginResult()
    }

    data class LoginData(
        var username : String,
        var password : String,
        var token : String,
        var profileId : String,
        var userId : String
    )
}