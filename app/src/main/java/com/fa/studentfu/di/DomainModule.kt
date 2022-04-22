package com.fa.studentfu.di

import com.fa.studentfu.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetGroupVariantsUseCase(get()) }
    factory { LoginUserUseCase(get()) }
    factory { SaveUserDataUseCase(get()) }
    factory { LogoutUserUseCase(get()) }
    factory { CheckUserAuthorizedUseCase(get()) }
    factory { GetProfileDataUseCase(get(), get())}
}