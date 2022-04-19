package com.fa.studentfu.presentation.sign.login

import android.graphics.Paint
import android.view.View
import androidx.navigation.fragment.findNavController
import com.fa.studentfu.R
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.databinding.RegistrationFragmentBinding
import com.fa.studentfu.presentation.extension.activityNavController
import com.fa.studentfu.presentation.extension.navigateSafely

class LoginFragment :
    BaseFragment<RegistrationFragmentBinding>(RegistrationFragmentBinding::inflate) {

    override fun initializeViews() {
        binding.sectionRegistration.visibility = View.GONE
        binding.registrationSwitch.text = resources.getString(R.string.goto_register)
        binding.registrationSwitch.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.register.text = resources.getString(R.string.login)
    }

    override fun setupListeners() {
        binding.registrationSwitch.setOnClickListener{
            findNavController().navigateSafely(LoginFragmentDirections.actionLoginToRegistration())
        }
        binding.register.setOnClickListener {
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }
    }
}