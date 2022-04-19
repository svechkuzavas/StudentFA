package com.fa.studentfu.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fa.studentfu.R
import com.fa.studentfu.core.ui.BaseFlowFragment
import com.fa.studentfu.databinding.MainFlowFragmentBinding
import com.fa.studentfu.presentation.main.news.NewsFragment
import com.fa.studentfu.presentation.main.profile.ProfileFragment
import com.fa.studentfu.presentation.main.reference.ReferenceFragment
import com.fa.studentfu.presentation.main.timetable.TimetableFragment

class MainFlowFragment : BaseFlowFragment(
    R.layout.main_flow_fragment){

    private val binding by viewBinding(MainFlowFragmentBinding::bind)

    @RequiresApi(Build.VERSION_CODES.S)
    override fun setupNavigation() {
        binding.bottomNavMain.setOnItemSelectedListener {
            item ->
            when (item.itemId){
                R.id.menu_news -> replaceFragment(NewsFragment())
                R.id.menu_profile -> replaceFragment(ProfileFragment())
                R.id.menu_reference -> replaceFragment(ReferenceFragment())
                R.id.menu_timetable -> replaceFragment(TimetableFragment())
                else -> true
            }
        }
    }

    private fun replaceFragment(fragment : Fragment) : Boolean {
        childFM.beginTransaction().replace(R.id.main_flow_container, fragment).commit()
        return true
    }
}