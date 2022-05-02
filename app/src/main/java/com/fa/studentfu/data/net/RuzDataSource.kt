package com.fa.studentfu.data.net

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.core.data.NoInternetException
import com.fa.studentfu.data.RuzApi
import com.fa.studentfu.data.models.RuzModel
import com.fa.studentfu.domain.models.GroupSearchModel
import com.fa.studentfu.domain.models.ScheduleModel
import java.lang.Exception

class RuzDataSource(private val ruzApi : RuzApi) {
    suspend fun fetchSearchResults(term : String) : BaseResult<List<GroupSearchModel>, Failure>{
        try {
            val response = ruzApi.searchGroup(input = term)
            return if (response.isSuccessful){
                val searchResults = mutableListOf<GroupSearchModel>()
                response.body()?.forEach{
                    result ->
                    searchResults.add(GroupSearchModel(
                        id = result.id,
                        label = result.label,
                        description = result.description
                    ))
                }
                BaseResult.Success(searchResults)
            } else {
                BaseResult.Error(Failure(response.code(), response.message()))
            }
        } catch (e : NoInternetException){
            return BaseResult.Error(Failure(0, e.message))
        } catch (e : Exception){
            return BaseResult.Error(Failure(1, e.message.toString()))
        }
    }

    suspend fun fetchSchedule(groupId : String, start : String, end : String):
            BaseResult<List<ScheduleModel>, Failure>{
        try {
            val response = ruzApi.fetchSchedule(groupId = groupId, startDate = start, endDate = end)
            return if (response.isSuccessful){
                val schedule = mutableListOf<ScheduleModel>()
                response.body()?.forEach{
                        result ->
                    val group : String = result.groups ?: result.group ?: ""
                    schedule.add(
                        ScheduleModel(
                        auditorium = result.auditorium,
                        time = "${result.startTime}-${result.endTime}",
                        building = result.building,
                        discipline = result.discipline,
                        type = result.type,
                        lecturer = result.lecturer,
                        groups = group,
                        url = result.url,
                        urlDescription = result.urlDescription
                    ))
                }
                BaseResult.Success(schedule)
            } else {
                BaseResult.Error(Failure(response.code(), response.message()))
            }
        } catch (e : NoInternetException){
            return BaseResult.Error(Failure(0, e.message))
        } catch (e : Exception){
            return BaseResult.Error(Failure(1, e.message.toString()))
        }
    }
}