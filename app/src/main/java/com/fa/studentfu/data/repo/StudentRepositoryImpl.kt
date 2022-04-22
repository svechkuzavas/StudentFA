package com.fa.studentfu.data.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.data.models.UserApiModel
import com.fa.studentfu.data.net.StudentApiDataSource
import com.fa.studentfu.domain.repo.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudentRepositoryImpl(private val studentApiDataSource: StudentApiDataSource)
    : StudentRepository {

    override suspend fun fetchToken(credentials: AuthorizationModel.UserCredentials):
            Flow<BaseResult<AuthorizationModel.AuthorizationToken, Failure>> {
        return flow{
            val result = studentApiDataSource.fetchToken(credentials)
            emit(result)
        }
    }

    override suspend fun fetchUserProfile(id : String):
            Flow<BaseResult<ProfileModel, Failure>> {
        return flow{
            val result = studentApiDataSource.fetchUserProfile(id)
            emit(result)
        }
    }
}