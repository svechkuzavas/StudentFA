package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.Resource

class LogoutUserUseCase(private val tokenManager: Resource.TokenManager) {
    suspend operator fun invoke() {
        tokenManager.clearToken()
    }
}