package com.fa.studentfu.presentaiton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.core.data.BaseResult
import com.fa.studentfu.domain.models.GroupSearchModel
import com.fa.studentfu.domain.usecase.GetGroupVariantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TestViewModel(private val getGroupVariantsUseCase: GetGroupVariantsUseCase) : ViewModel() {

    private val _groups = MutableStateFlow(mutableListOf<GroupSearchModel>())

    fun getGroups(term : String){
        viewModelScope.launch {
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
        }
    }

    fun observeGroups() : StateFlow<List<GroupSearchModel>> = _groups
}