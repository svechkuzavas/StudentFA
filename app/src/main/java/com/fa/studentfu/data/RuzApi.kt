package com.fa.studentfu.data

import com.fa.studentfu.data.models.RuzModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RuzApi {
    @GET("search?")
    suspend fun searchGroup(
        @Query("term") input : String,
        @Query("type") group : String = "group") : Response<List<RuzModel.GroupSearch>>

    @GET("schedule/group/{group}?")
    suspend fun fetchSchedule(
        @Path("group") groupId : String,
        @Query("start") startDate : String,
        @Query("finish") endDate : String,
        @Query("lng") languageCode : String = "1"
    ) : Response<List<RuzModel.ScheduleModel>>
}