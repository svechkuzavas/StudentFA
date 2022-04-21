package com.fa.studentfu.presentation.main.profile

import com.fa.studentfu.R
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.databinding.ProfileFragmentBinding
import com.fa.studentfu.presentation.extension.activityNavController
import com.fa.studentfu.presentation.extension.navigateSafely
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::inflate) {

    private val viewModel by viewModel<ProfileViewModel>()

    override fun initializeViews() {
        super.initializeViews()
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.profileLogout?.setOnClickListener {
            viewModel.logout()
        }
    }

    override fun observe() {
        super.observe()
        viewModel.state.observe(viewLifecycleOwner){ state ->
            when (state) {
                is ProfileViewModel.UiState.LogOut -> {
                    activityNavController().navigateSafely(R.id.action_global_signFlowFragment)
                }
            }
        }
    }
}