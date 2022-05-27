package com.fa.studentfu.domain.repo

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.data.models.ReferenceModel
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun fetchToken(credentials: AuthorizationModel.UserCredentials)
            : Flow<BaseResult<AuthorizationModel.AuthorizationToken, Failure>>

    suspend fun fetchUserProfile(id : String)
            : Flow<BaseResult<ProfileModel, Failure>>

    suspend fun fetchNews() : Flow<BaseResult<List<ArticleModel>, Failure>>
    suspend fun fetchReferences() : Flow<BaseResult<List<ReferenceModel>, Failure>>
}