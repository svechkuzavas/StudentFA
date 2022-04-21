package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.domain.repo.StudentRepository
import kotlinx.coroutines.flow.Flow

class LoginUserUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(credentials: AuthorizationModel.UserCredentials) :
            Flow<BaseResult<AuthorizationModel.AuthorizationToken, Failure>> {
        return studentRepository.fetchToken(credentials)
    }
}