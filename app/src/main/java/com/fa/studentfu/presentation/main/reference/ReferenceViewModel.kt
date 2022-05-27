package com.fa.studentfu.presentation.main.reference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.data.models.ReferenceModel
import com.fa.studentfu.domain.usecase.GetReferencesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReferenceViewModel(
    private val getReferencesUseCase: GetReferencesUseCase
) : ViewModel() {
    private val _state = MutableLiveData<UiState>(UiState.Loading)
    val state: LiveData<UiState>
        get() = _state

    fun fetchReferences(){
        _state.postValue(UiState.Loading)
        viewModelScope.launch {
            getReferencesUseCase().collect {
                _state.postValue(UiState.ResultAvailable(it))
            }
        }
    }

    sealed class UiState{
        object Loading : UiState()
        class ResultAvailable(val references : List<ReferenceModel>) : UiState()
    }
}