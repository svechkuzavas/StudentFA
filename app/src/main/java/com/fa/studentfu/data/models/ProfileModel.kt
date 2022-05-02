package com.fa.studentfu.data.models

import com.google.gson.annotations.SerializedName

data class ProfileModel(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("group") val group: String,
    @SerializedName("role") val role: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("description") val description: String
)