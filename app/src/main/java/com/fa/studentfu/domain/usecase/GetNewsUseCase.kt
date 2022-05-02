package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.domain.repo.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetNewsUseCase(private val studentRepository: StudentRepository) {
    suspend operator fun invoke(): Flow<List<ArticleModel>> {
        val result = studentRepository.fetchNews()
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