package com.fa.studentfu.domain.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.RuzModel
import com.fa.studentfu.domain.models.GroupSearchModel
import com.fa.studentfu.domain.models.ScheduleModel
import kotlinx.coroutines.flow.Flow

interface RuzRepository {
    suspend fun fetchSearchResults(term : String) : Flow<BaseResult<List<GroupSearchModel>, Failure>>
    suspend fun fetchSchedule(groupId : String, startDate : String, endDate : String) :
            Flow<BaseResult<List<ScheduleModel>,Failure>>
}