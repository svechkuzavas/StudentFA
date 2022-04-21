package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.Resource

class SaveTokenUseCase(private val tokenManager : Resource.TokenManager) {
    suspend operator fun invoke(token : String){
        tokenManager.saveToken(token)
    }
}