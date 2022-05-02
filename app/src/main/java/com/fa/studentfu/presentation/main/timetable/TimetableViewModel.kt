package com.fa.studentfu.presentation.main.timetable

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.studentfu.domain.models.ScheduleModel
import com.fa.studentfu.domain.usecase.GetScheduleUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TimetableViewModel(private val getScheduleUseCase: GetScheduleUseCase) : ViewModel(){

    private val _state = MutableLiveData<UiState>(UiState.Loading)
    val state: LiveData<UiState>
        get() = _state

    fun loadSchedule(startDate : String, endDate : String){
        _state.postValue(UiState.Loading)
        viewModelScope.launch {
            getScheduleUseCase(startDate, endDate).collect {
                _state.postValue(UiState.ResultAvailable(it))
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        class ResultAvailable(val scheduleList : List<ScheduleModel>): UiState()
    }
}
