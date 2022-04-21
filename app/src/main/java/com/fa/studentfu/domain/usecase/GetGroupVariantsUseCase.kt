package com.fa.studentfu.domain.usecase

import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.core.data.Failure
import com.fa.studentfu.domain.models.GroupSearchModel
import com.fa.studentfu.domain.repo.RuzRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class GetGroupVariantsUseCase(
    private val ruzRepository: RuzRepository
) {

    suspend operator fun invoke(term : String) : Flow<BaseResult<List<GroupSearchModel>, Failure>>{
        return ruzRepository.fetchSearchResults(term)
    }
}

/*viewModelScope.launch {
    getGroupVariantsUseCase(term)
        .collect {
                result ->
            when (result) {
                is BaseResult.Success ->{
                    _groups.value = result.data as MutableList<GroupSearchModel>
                }
                is BaseResult.Error -> {

                }
            }
        }
}*/