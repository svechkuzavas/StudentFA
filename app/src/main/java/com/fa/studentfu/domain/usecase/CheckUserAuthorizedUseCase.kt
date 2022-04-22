package com.fa.studentfu.domain.usecase

import com.fa.studentfu.domain.repo.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckUserAuthorizedUseCase(private val userDataRepository: UserDataRepository) {
    operator fun invoke() : Flow<Boolean> {
        return flow {
            val result = userDataRepository.getToken().isNotEmpty()
            emit(result)
        }
    }
}