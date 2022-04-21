package com.fa.studentfu.data.net

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.core.data.NoConnectivityException
import com.fa.studentfu.core.data.NoInternetException
import com.fa.studentfu.data.StudentApi
import com.fa.studentfu.data.models.AuthorizationModel
import java.lang.Exception

class StudentApiDataSource(private val studentApi : StudentApi) {

    suspend fun fetchToken(credentials : AuthorizationModel.UserCredentials)
    : BaseResult<AuthorizationModel.AuthorizationToken, Failure> {
        try {
            val response = studentApi.loginUser(credentials)
            var token = ""
            return when {
                response.isSuccessful -> {
                    response.body()?.let{ result ->
                        token = result.token
                    }
                    BaseResult.Success(AuthorizationModel.AuthorizationToken(token))
                }
                response.code() == 400 -> {
                    BaseResult.Error(Failure(response.code(), "Ошибка авторизации. Проверьте данные"))
                }
                else -> {
                    BaseResult.Error(Failure(response.code(), response.message()))
                }
            }
        } catch (e : NoInternetException){
            return BaseResult.Error(Failure(0, e.message))
        } catch (e : NoConnectivityException){
            return BaseResult.Error(Failure(1, e.message))
        } catch (e : Exception){
            return BaseResult.Error(Failure(2, e.message.toString()))
        }
    }

}