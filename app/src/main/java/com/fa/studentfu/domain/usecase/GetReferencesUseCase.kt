package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.data.models.ReferenceModel
import com.fa.studentfu.domain.repo.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetReferencesUseCase(private val studentRepository: StudentRepository){
    suspend operator fun invoke(): Flow<List<ReferenceModel>> {
        val result = studentRepository.fetchReferences()
        return flow {
            result.collect {
                when(it){
                    is BaseResult.Success -> {
                        emit(it.data)
                    }
                }
            }
        }
    }
}