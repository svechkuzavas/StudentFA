package com.fa.studentfu.data.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.net.RuzApiDataSource
import com.fa.studentfu.domain.models.GroupSearchModel
import com.fa.studentfu.domain.models.ScheduleModel
import com.fa.studentfu.domain.repo.RuzRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RuzRepositoryImpl(
    private val ruzApiDataSource: RuzApiDataSource
): RuzRepository{

    override suspend fun fetchSearchResults(term: String): Flow<BaseResult<List<GroupSearchModel>, Failure>> {
        return flow {
            val result = ruzApiDataSource.fetchSearchResults(term)
            if (result is BaseResult.Success){
                emit(result)
            }
        }
    }

    override suspend fun fetchSchedule(
        groupId: String,
        startDate: String,
        endDate: String
    ): Flow<BaseResult<List<ScheduleModel>, Failure>> {
        return flow {
            val result = ruzApiDataSource.fetchSchedule(groupId, startDate, endDate)
            if (result is BaseResult.Success) {
                emit(result)
            }
        }
    }
}