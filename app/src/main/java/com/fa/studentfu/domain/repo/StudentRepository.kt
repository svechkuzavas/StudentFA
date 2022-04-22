package com.fa.studentfu.domain.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.data.models.UserApiModel
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun fetchToken(credentials: AuthorizationModel.UserCredentials)
            : Flow<BaseResult<AuthorizationModel.AuthorizationToken, Failure>>

    suspend fun fetchUserProfile(id : String)
            : Flow<BaseResult<ProfileModel, Failure>>
}