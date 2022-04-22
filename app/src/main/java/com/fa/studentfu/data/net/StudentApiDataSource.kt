package com.fa.studentfu.data.net

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.core.data.NoConnectivityException
import com.fa.studentfu.core.data.NoInternetException
import com.fa.studentfu.data.StudentApi
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.data.models.UserApiModel
import java.lang.Exception

class StudentApiDataSource(private val studentApi : StudentApi) {

    suspend fun fetchToken(credentials : AuthorizationModel.UserCredentials)
    : BaseResult<AuthorizationModel.AuthorizationToken, Failure> {
        try {
            val response = studentApi.loginUser(credentials)
            var token = ""
            var userId = ""
            var profileId = ""
            return when {
                response.isSuccessful -> {
                    response.body()?.let{ result ->
                        token = result.token
                        userId = result.userId
                        profileId = result.profileId
                    }
                    BaseResult.Success(AuthorizationModel.AuthorizationToken(token, userId, profileId))
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

    suspend fun fetchUserProfile(id : String)
    : BaseResult<ProfileModel, Failure> {
        try {
            val response = studentApi.fetchUserProfile(id)
            var profileModel = ProfileModel("", "", "", "", "", "")
            return when {
                response.isSuccessful -> {
                    response.body()?.let { result ->
                        profileModel = ProfileModel(
                            firstName = result.firstName,
                            role = result.role,
                            imageUrl = result.imageUrl,
                            group = result.group,
                            lastName = result.lastName,
                            email = result.email
                        )
                    }
                    BaseResult.Success(profileModel)
                }
                response.code() == 400 -> {
                    BaseResult.Error(Failure(response.code(), "Ошибка сервера"))
                }
                response.code() == 401 -> {
                    BaseResult.Error(Failure(response.code(), "Вы не авторизованы"))
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