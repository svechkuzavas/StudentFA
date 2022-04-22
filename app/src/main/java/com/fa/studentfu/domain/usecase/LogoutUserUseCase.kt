package com.fa.studentfu.domain.usecase

import com.fa.studentfu.domain.repo.UserDataRepository

class LogoutUserUseCase(private val userDataRepository: UserDataRepository) {
    suspend operator fun invoke() {
        userDataRepository.clearUserData()
    }
}