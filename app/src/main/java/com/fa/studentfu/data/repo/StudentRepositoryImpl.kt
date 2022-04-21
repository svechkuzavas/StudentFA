package com.fa.studentfu.data.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.net.StudentApiDataSource
import com.fa.studentfu.domain.repo.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudentRepositoryImpl(private val studentApiDataSource: StudentApiDataSource)
    : StudentRepository {
    override suspend fun fetchToken(credentials: AuthorizationModel.UserCredentials)
    : Flow<BaseResult<AuthorizationModel.AuthorizationToken, Failure>> {
        return flow{
            when (val result = studentApiDataSource.fetchToken(credentials)){
                is BaseResult.Success -> emit(result)
                is BaseResult.Error -> emit(result)
            }
        }
    }
}