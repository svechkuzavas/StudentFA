package com.fa.studentfu.domain.usecase

import com.fa.studentfu.domain.repo.UserDataRepository

class SaveUserDataUseCase(private val userDataRepository: UserDataRepository) {
    suspend operator fun invoke(token : String, profileId : String, userId : String){
        userDataRepository.saveToken(token)
        userDataRepository.saveId(profileId)
        userDataRepository.saveUserId(userId)
    }
}