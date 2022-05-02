package com.fa.studentfu.data.models

import com.google.gson.annotations.SerializedName

sealed class RuzModel {
    class GroupSearch(
        @SerializedName("id") val id : String,
        @SerializedName("label") val label : String,
        @SerializedName("description") val description : String
    ) : RuzModel()
    class ScheduleModel(
        @SerializedName("auditorium") val auditorium : String,
        @SerializedName("beginLesson") val startTime : String,
        @SerializedName("building") val building : String,
        @SerializedName("discipline") val discipline : String,
        @SerializedName("endLesson") val endTime : String,
        @SerializedName("group") val group : String?,
        @SerializedName("kindOfWork") val type : String,
        @SerializedName("lecturer") val lecturer : String,
        @SerializedName("stream") val groups : String?,
        @SerializedName("url1") val url : String,
        @SerializedName("url1_description") val urlDescription : String,
    ) : RuzModel()
}