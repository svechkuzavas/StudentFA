package com.fa.studentfu.data

import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.models.ProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentApi {
    @POST("api-token-auth/")
    suspend fun loginUser(@Body credentials: AuthorizationModel.UserCredentials): Response<AuthorizationModel.AuthorizationToken>

    @POST("register/")
    suspend fun registerUser(registrationData: AuthorizationModel.Registration): Response<AuthorizationModel.Registration>

    @GET("user_profile/{id}/get_full_data/")
    suspend fun fetchUserProfile(@Path("id") id : String) : Response<ProfileModel>

    @GET("article/")
    suspend fun fetchArticles() : Response<List<ArticleModel>>
}

