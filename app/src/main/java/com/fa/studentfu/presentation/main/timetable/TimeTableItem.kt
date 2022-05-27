package com.fa.studentfu.presentation.main.timetable

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.fa.studentfu.R
import com.fa.studentfu.databinding.TimetableListElementBinding
import com.fa.studentfu.domain.models.ScheduleModel
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class TimeTableItem(private val scheduleModel: ScheduleModel)
    : AbstractBindingItem<TimetableListElementBinding>(){

    override val type: Int
        get() = R.id.timetable_recycler

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): TimetableListElementBinding {
        return TimetableListElementBinding.inflate(inflater, parent, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun bindView(binding: TimetableListElementBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        handleType(binding)
        binding.timetableBuilding.text = scheduleModel.building
        binding.timetableGroups.text = scheduleModel.groups
        binding.timetableLecture.text = scheduleModel.discipline
        binding.timetableLecturer.text = scheduleModel.lecturer
        binding.timetableType.text = scheduleModel.type
        binding.timetableGroups.text = scheduleModel.groups
        binding.timetableAuditorium.text = scheduleModel.auditorium
        binding.timetableTime.text = scheduleModel.time
    }

    private fun handleType(binding: TimetableListElementBinding){
        var color = Color.parseColor("#6BE88E")
        if (binding.timetableType.text == "Семинар"){
            color = Color.parseColor("#EFCA6B")
        }
        binding.timetableType.compoundDrawables.forEach {
            it?.setTint(color)
        }
    }
}