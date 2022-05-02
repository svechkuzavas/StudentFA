package com.fa.studentfu.presentation.main.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(val getNewsUseCase: GetNewsUseCase) : ViewModel(){

    private val _state = MutableLiveData<UiState>(UiState.Loading)
    val state: LiveData<UiState>
        get() = _state

    fun fetchNews(){
        _state.postValue(UiState.Loading)
        viewModelScope.launch {
            getNewsUseCase().collect {
                _state.postValue(UiState.ResultAvailable(it))
            }
        }
    }

    sealed class UiState{
        object Loading : UiState()
        class ResultAvailable(val articles : List<ArticleModel>) : UiState()
    }
}