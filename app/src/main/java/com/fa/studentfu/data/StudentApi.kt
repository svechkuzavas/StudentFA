package com.fa.studentfu.data

import com.fa.studentfu.data.models.AuthorizationModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentApi {
    @POST("api-token-auth")
    suspend fun loginUser(credentials: AuthorizationModel.UserCredentials) : Result<AuthorizationModel.AuthorizationToken>
    @POST("register")
    suspend fun registerUser(registrationData : AuthorizationModel.Registration) : Result<AuthorizationModel.Registration>
}