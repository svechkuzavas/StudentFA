package com.fa.studentfu.domain.usecase

import android.util.Log
import com.fa.studentfu.core.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckUserAuthorizedUseCase(private val tokenManager: Resource.TokenManager) {
    operator fun invoke() : Flow<Boolean> {
        return flow {
            val result = tokenManager.getToken().isNotEmpty()
            emit(result)
        }
    }
}