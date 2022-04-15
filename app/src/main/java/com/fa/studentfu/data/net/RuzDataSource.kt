package com.fa.studentfu.data.net

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.core.data.NoInternetConnectionException
import com.fa.studentfu.data.RuzApi
import com.fa.studentfu.data.models.RuzModel
import com.fa.studentfu.domain.models.GroupSearchModel
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
        } catch (e : NoInternetConnectionException){
            return BaseResult.Error(Failure(0, e.message))
        } catch (e : Exception){
            return BaseResult.Error(Failure(1, e.message.toString()))
        }
    }
}