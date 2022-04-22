package com.fa.studentfu.data.models

import com.google.gson.annotations.SerializedName

data class UserApiModel(
    @SerializedName("username") val username : String,
    @SerializedName("email") val email : String,
    @SerializedName("first_name") val firstName : String,
    @SerializedName("last_name") val lastName : String
)