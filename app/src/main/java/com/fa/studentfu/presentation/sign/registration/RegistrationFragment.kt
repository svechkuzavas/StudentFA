package com.fa.studentfu.presentation.sign.registration

import android.view.View
import androidx.navigation.fragment.findNavController
import com.fa.studentfu.R
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.databinding.RegistrationFragmentBinding
import com.fa.studentfu.presentation.extension.activityNavController
import com.fa.studentfu.presentation.extension.navigateSafely

class RegistrationFragment :
    BaseFragment<RegistrationFragmentBinding>(RegistrationFragmentBinding::inflate) {

    override fun initializeViews() {
        binding.sectionRegistration.visibility = View.VISIBLE
    }

    override fun setupListeners() {
        binding.registrationSwitch.setOnClickListener {
            findNavController().navigateSafely(RegistrationFragmentDirections.actionRegistrationToLogin())
        }
        binding.register.setOnClickListener {
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }
    }
}