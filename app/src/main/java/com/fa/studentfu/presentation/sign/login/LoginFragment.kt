package com.fa.studentfu.presentation.sign.login

import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.fa.studentfu.R
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.databinding.RegistrationFragmentBinding
import com.fa.studentfu.presentation.extension.activityNavController
import com.fa.studentfu.presentation.extension.navigateSafely
import com.fa.studentfu.presentation.extension.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment :
    BaseFragment<RegistrationFragmentBinding>(RegistrationFragmentBinding::inflate) {

    private val viewModel by viewModel<LoginViewModel>()

    override fun initializeViews() {
        binding.sectionRegistration.visibility = View.GONE
        binding.registrationSwitch.text = resources.getString(R.string.goto_register)
        binding.registrationSwitch.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.register.text = resources.getString(R.string.login)
    }

    override fun observe() {
        viewModel.state.observe(viewLifecycleOwner){ state ->
            setLoadingState(false)
            when (state) {
                is LoginViewModel.UiState.Input -> {

                }
                is LoginViewModel.UiState.Loading -> {
                    setLoadingState(true)
                }
                is LoginViewModel.UiState.ResultAvailable -> {
                    if (state.result is LoginViewModel.LoginResult.Error){
                        showSnackBar(state.result.message, binding.root)
                    } else{
                        viewModel.saveUserData()
                        activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
                    }
                }
            }
        }
    }

    override fun setupListeners() {
        binding.register.setOnClickListener{
            viewModel.login()
        }

        binding.registrationSwitch.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginToRegistration())
        }


        binding.regUsername.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setUsername(s.toString())
            }
        })

        binding.regPassword.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setPassword(s.toString())
            }
        })
    }

    fun setLoadingState(isLoading : Boolean){
        binding.loadingDisable?.referencedIds?.forEach {
            val v = view?.findViewById<View>(it)
            v?.isEnabled = !isLoading
        }
        binding.loadingProgress?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}