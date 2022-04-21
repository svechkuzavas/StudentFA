package com.fa.studentfu.presentation.sign.splash

import com.fa.studentfu.R
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.databinding.SplashFragmentBinding
import com.fa.studentfu.presentation.extension.activityNavController
import com.fa.studentfu.presentation.extension.navigateSafely
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashFragmentBinding>(SplashFragmentBinding::inflate) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun initializeViews() {
        super.initializeViews()
        viewModel.preloadApplication()
    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun observe() {
        super.observe()
        viewModel.state.observe(viewLifecycleOwner){
            state ->
            when (state){
                is SplashViewModel.UiState.Loading -> {

                }
                is SplashViewModel.UiState.ResultAvailable -> {
                    handleAuthority(state.isAuthorizeed)
                }
            }
        }
    }

    private fun handleAuthority(isAuthorized : Boolean) {
        if (isAuthorized) {
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        } else {
            activityNavController().navigateSafely(R.id.action_global_signFlowFragment)
        }
    }
}