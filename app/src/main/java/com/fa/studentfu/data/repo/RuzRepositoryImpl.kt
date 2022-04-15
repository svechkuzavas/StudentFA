package com.fa.studentfu.data.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.RuzModel
import com.fa.studentfu.data.net.RuzDataSource
import com.fa.studentfu.domain.models.GroupSearchModel
import com.fa.studentfu.domain.repo.RuzRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RuzRepositoryImpl(
    private val ruzDataSource: RuzDataSource
): RuzRepository{

    override suspend fun fetchSearchResults(term: String): Flow<BaseResult<List<GroupSearchModel>, Failure>> {
        return flow {
            val result = ruzDataSource.fetchSearchResults(term)
            if (result is BaseResult.Success){
                emit(result)
            }
        }
    }

}