package com.fa.studentfu.data.models

import com.google.gson.annotations.SerializedName

sealed class AuthorizationModel{
    data class UserCredentials(
        @SerializedName("username") val userName : String,
        @SerializedName("password") val password : String
    ) : AuthorizationModel()

    data class AuthorizationToken(
        @SerializedName("token") val token : String,
        @SerializedName("user_id") val userId : String,
        @SerializedName("profile_id") val profileId : String
    ) : AuthorizationModel()

    data class Registration(
        @SerializedName("username") val userName : String,
        @SerializedName("password") val password : String,
        @SerializedName("email") val email : String
    ) : AuthorizationModel()
}
