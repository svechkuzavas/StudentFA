package com.fa.studentfu.presentation.main.reference

import androidx.navigation.fragment.findNavController
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.databinding.ReferencesFragmentBinding
import com.fa.studentfu.presentation.extension.navigateSafely

class ReferenceFragment : BaseFragment<ReferencesFragmentBinding>(ReferencesFragmentBinding::inflate){
    override fun initializeViews() {
        super.initializeViews()
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.createReference.setOnClickListener {
            findNavController().navigateSafely(ReferenceFragmentDirections.actionReferenceToReferenceAdd())
        }
    }

    override fun observe() {
        super.observe()
    }
}