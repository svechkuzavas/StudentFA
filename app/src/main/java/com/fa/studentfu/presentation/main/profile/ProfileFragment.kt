package com.fa.studentfu.presentation.main.profile

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.fa.studentfu.BuildConfig
import com.fa.studentfu.R
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.databinding.ProfileFragmentBinding
import com.fa.studentfu.presentation.extension.activityNavController
import com.fa.studentfu.presentation.extension.navigateSafely
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::inflate) {

    private val viewModel by viewModel<ProfileViewModel>()

    override fun initializeViews() {
        super.initializeViews()
        viewModel.fetchUser()
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
                is ProfileViewModel.UiState.ResultAvailable -> {
                    setupProfileView(state.model)
                    loadProfileImage(state.model.imageUrl)
                }
            }
        }
    }

    private fun setupProfileView(model : ProfileModel){
        binding.profileName.text = model.firstName
        binding.profileSurname.text = model.lastName
        binding.profileEmail.text = model.email
        binding.profileGroup.text = model.group
        binding.profileDescription.text = model.description
    }

    @SuppressLint("CheckResult")
    private fun loadProfileImage(imageUrl : String){
        Glide.with(this)
            .load("${BuildConfig.STUDENT_API_URL}${imageUrl}").into(binding.profileImage)
    }
}