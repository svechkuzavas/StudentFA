package com.fa.studentfu.di

import com.fa.studentfu.presentation.main.profile.ProfileViewModel
import com.fa.studentfu.presentation.sign.login.LoginViewModel
import com.fa.studentfu.presentation.sign.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
}