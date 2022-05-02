package com.fa.studentfu.presentation.main.timetable

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.databinding.TimetableFragmentBinding
import com.fa.studentfu.domain.models.ScheduleModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimetableFragment : BaseFragment<TimetableFragmentBinding>(TimetableFragmentBinding::inflate) {

    private val viewModel by viewModel<TimetableViewModel>()

    private var timetableAdapter  = ItemAdapter<TimeTableItem>()

    override fun initializeViews() {
        super.initializeViews()
        setupRecycler()
        viewModel.loadSchedule("2022.05.05", "2022.05.05")
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.timetableBtnToday?.setOnClickListener{
            clearRecycler()
            viewModel.loadSchedule("2022.05.05", "2022.05.05")
        }
        binding.timetableBtnTomorrow?.setOnClickListener{
            clearRecycler()
            viewModel.loadSchedule("2022.05.06", "2022.05.06")
        }
    }

    override fun observe() {
        super.observe()
        viewModel.state.observe(viewLifecycleOwner){
            when (it){
                is TimetableViewModel.UiState.Loading -> { binding.timetableLoader?.visibility = View.VISIBLE }
                is TimetableViewModel.UiState.ResultAvailable -> {
                    binding.timetableLoader?.visibility = View.GONE
                    refreshRecycler(it.scheduleList)
                }
            }
        }
    }

    private fun setupRecycler(){
        binding.timetableRecycler?.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        val timetableFastAdapter = FastAdapter.with(timetableAdapter)
        binding.timetableRecycler?.adapter = timetableFastAdapter
    }

    private fun refreshRecycler(scheduleList : List<ScheduleModel>){
        FastAdapterDiffUtil[timetableAdapter] = scheduleList.map(::TimeTableItem)
    }

    private fun clearRecycler(){
        FastAdapterDiffUtil[timetableAdapter] = emptyList<ScheduleModel>().map(::TimeTableItem)
    }
}