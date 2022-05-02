package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.domain.models.ScheduleModel
import com.fa.studentfu.domain.repo.RuzRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetScheduleUseCase(private val ruzRepository: RuzRepository) {
    suspend operator fun invoke(startDate : String, endDate : String) : Flow<List<ScheduleModel>> {
        val scheduleList = ruzRepository
            .fetchSchedule(groupId = "11987", startDate = startDate, endDate = endDate)
        return flow {
            scheduleList.collect {
                when (it){
                    is BaseResult.Success ->{
                        emit(it.data)
                    }
                }
            }
        }
    }
}