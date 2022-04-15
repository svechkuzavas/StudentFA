package com.fa.studentfu.di

import com.fa.studentfu.domain.usecase.GetGroupVariantsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetGroupVariantsUseCase(get()) }
}