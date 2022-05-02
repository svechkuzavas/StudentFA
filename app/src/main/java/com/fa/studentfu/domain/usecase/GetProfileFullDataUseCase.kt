package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.domain.repo.StudentRepository
import com.fa.studentfu.domain.repo.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetProfileFullDataUseCase(
    private val studentRepository: StudentRepository,
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke() : Flow<ProfileModel> {
        val profile = studentRepository.fetchUserProfile(userDataRepository.getProfileId())
        return flow {
            profile.collect {
                when (it){
                    is BaseResult.Success ->{
                        emit(it.data)
                    }
                }
            }
        }
    }
}