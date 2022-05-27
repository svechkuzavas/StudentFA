package com.fa.studentfu.data.models

import com.google.gson.annotations.SerializedName

data class ReferenceModel(
    @SerializedName("id") val id: Int,
    @SerializedName("date") val date: String,
    @SerializedName("stud") val stud: Int,
    @SerializedName("status") val status: String,
    @SerializedName("edu_form") val eduForm: String,
    @SerializedName("location") val location: String,
    @SerializedName("department") val department: String,
    @SerializedName("pickup_method") val pickupMethod: String,
    @SerializedName("organization_for") val organizationFor: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("owner") val owner: Int
)