package com.fa.studentfu.di

import com.fa.studentfu.presentaiton.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<TestViewModel> { TestViewModel(get()) }
}