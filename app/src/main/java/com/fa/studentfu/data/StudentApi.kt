package com.fa.studentfu.data

import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.data.models.ReferenceModel
import retrofit2.Response
import retrofit2.http.*

interface StudentApi {
    @POST("api-token-auth/")
    suspend fun loginUser(@Body credentials: AuthorizationModel.UserCredentials): Response<AuthorizationModel.AuthorizationToken>

    @POST("register/")
    suspend fun registerUser(registrationData: AuthorizationModel.Registration): Response<AuthorizationModel.Registration>

    @GET("user_profile/{id}/get_full_data/")
    suspend fun fetchUserProfile(@Path("id") id : String) : Response<ProfileModel>

    @GET("article/")
    suspend fun fetchArticles() : Response<List<ArticleModel>>

    @GET("reference")
    suspend fun fetchReferencesUser(@Query("user") userId : String) : Response<List<ReferenceModel>>
}
