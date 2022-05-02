package com.fa.studentfu.presentation.main.reference

import android.util.Log
import com.fa.studentfu.core.ui.BaseBottomSheetDialogFragment
import com.fa.studentfu.databinding.ReferenceAddFragmentBinding

class ReferenceAddBottomSheetDialog :
    BaseBottomSheetDialogFragment<ReferenceAddFragmentBinding>(ReferenceAddFragmentBinding::inflate) {
    override fun initializeViews() {
        super.initializeViews()
        Log.d( "initializeViews: ", "")
    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun observe() {
        super.observe()
    }
}