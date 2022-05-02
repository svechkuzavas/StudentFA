package com.fa.studentfu.data.models

import com.google.gson.annotations.SerializedName

class ArticleModel (
    @SerializedName("author") val author : String,
    @SerializedName("image") val imageUrl : String,
    @SerializedName("header") val header : String,
    @SerializedName("description") val description : String,
    @SerializedName("created_on") val dateTime : String
)