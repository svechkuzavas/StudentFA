package com.fa.studentfu.di

import android.content.Context
import com.fa.studentfu.core.data.Resource
import com.fa.studentfu.core.interceptors.BaseInterceptor
import com.fa.studentfu.core.interceptors.TokenAuthInterceptor
import com.fa.studentfu.data.RuzApi
import com.fa.studentfu.data.StudentApi
import com.fa.studentfu.data.net.*
import com.fa.studentfu.data.repo.RuzRepositoryImpl
import com.fa.studentfu.data.repo.StudentRepositoryImpl
import com.fa.studentfu.data.repo.UserDataRepositoryImpl
import com.fa.studentfu.data.sharedPref.SharedPrefTokenStorage
import com.fa.studentfu.domain.repo.RuzRepository
import com.fa.studentfu.domain.repo.StudentRepository
import com.fa.studentfu.domain.repo.UserDataRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory<BaseInterceptor> { BaseInterceptor(androidContext()) }
    factory<TokenAuthInterceptor> { TokenAuthInterceptor(get()) }
    factory<HttpLoggingInterceptor> { provideHttpLoggingInterceptor() }

    factory<OkHttpClientInst.Base> { provideOkHttpClientBase(get(), get()) }
    factory<OkHttpClientInst.TokenAuth> { provideOkHttpClientTokenAuthorization(get(), get(), get()) }

    single<RetrofitInst.Student> { provideRetrofitStudentApi(get()) }
    single<RetrofitInst.Ruz> { provideRetrofitRuzApi(get())}

    factory<StudentApi> { provideStudentApi(get()) }
    factory<RuzApi> { provideRuzApi(get()) }

    factory<RuzApiDataSource> { provideRuzDataSource(get()) }
    factory<RuzRepository> { provideRuzRepository(get()) }

    factory<StudentApiDataSource> { provideStudentApiDataSource(get()) }
    factory<StudentRepository> { provideStudentRepository(get()) }

    factory<Resource.UserData> { provideSharedPrefUserDataSource(androidContext()) }
    factory<UserDataRepository> {provideUserDataRepository(get())}
}

fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return logging
}

fun provideOkHttpClientBase(
    loggingInterceptor: HttpLoggingInterceptor,
    baseInterceptor: BaseInterceptor) : OkHttpClientInst.Base {
    return OkHttpClientInst.Base(loggingInterceptor, baseInterceptor)
}

fun provideOkHttpClientTokenAuthorization(
    loggingInterceptor: HttpLoggingInterceptor,
    baseInterceptor: BaseInterceptor,
    tokenAuthInterceptor : TokenAuthInterceptor
): OkHttpClientInst.TokenAuth {
    return OkHttpClientInst.TokenAuth(loggingInterceptor, baseInterceptor, tokenAuthInterceptor)
}

fun provideRetrofitStudentApi(okHttpClient: OkHttpClientInst.TokenAuth) : RetrofitInst.Student =
    RetrofitInst.Student(okHttpClient.client)

fun provideRetrofitRuzApi(okHttpClient: OkHttpClientInst.Base): RetrofitInst.Ruz =
    RetrofitInst.Ruz(okHttpClient.client)

fun provideStudentApi(retrofit: RetrofitInst.Student) : StudentApi = retrofit.retrofit.create(StudentApi::class.java)

fun provideRuzApi(retrofit: RetrofitInst.Ruz) : RuzApi = retrofit.retrofit.create(RuzApi::class.java)

fun provideRuzDataSource(ruzApi: RuzApi) : RuzApiDataSource =
    RuzApiDataSource(ruzApi)

fun provideStudentApiDataSource(studentApi: StudentApi) : StudentApiDataSource =
    StudentApiDataSource(studentApi)

fun provideRuzRepository(ruzApiDataSource: RuzApiDataSource) : RuzRepository =
    RuzRepositoryImpl(ruzApiDataSource)

fun provideStudentRepository(studentApiDataSource: StudentApiDataSource) : StudentRepository =
    StudentRepositoryImpl(studentApiDataSource)

fun provideUserDataRepository(userDataDataSource : Resource.UserData) : UserDataRepository =
    UserDataRepositoryImpl(userDataDataSource)

fun provideSharedPrefUserDataSource(context : Context) = SharedPrefTokenStorage(context)