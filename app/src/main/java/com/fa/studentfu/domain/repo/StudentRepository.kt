package com.fa.studentfu.domain.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.AuthorizationModel
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun fetchToken(credentials: AuthorizationModel.UserCredentials)
    : Flow<BaseResult<AuthorizationModel.AuthorizationToken, Failure>>
}