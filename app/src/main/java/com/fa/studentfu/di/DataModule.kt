package com.fa.studentfu.di

import com.fa.studentfu.BuildConfig
import com.fa.studentfu.data.RuzApi
import com.fa.studentfu.data.StudentApi
import com.fa.studentfu.data.net.BaseInterceptor
import com.fa.studentfu.data.net.RuzDataSource
import com.fa.studentfu.data.net.TokenAuthInterceptor
import com.fa.studentfu.data.repo.RuzRepositoryImpl
import com.fa.studentfu.domain.repo.RuzRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory<BaseInterceptor> { BaseInterceptor(androidContext()) }
    factory<TokenAuthInterceptor> { TokenAuthInterceptor(get()) }
    factory { provideOkHttpClientTokenAuthorization(get(), get()) }

    //single { provideRetrofitStudentApi(get()) }
    single { provideRetrofitRuzApi(provideOkHttpClientBase(get()))}

    factory { provideStudentApi(get()) }
    factory { provideRuzApi(get()) }

    factory { provideRuzDataSource(get()) }
    factory<RuzRepository> { provideRuzRepository(get()) }
}

fun provideOkHttpClientBase(baseInterceptor: BaseInterceptor) : OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(baseInterceptor)
        .build()
}

fun provideOkHttpClientTokenAuthorization(
    baseClient : OkHttpClient,
    tokenAuthInterceptor : TokenAuthInterceptor): OkHttpClient {
    return baseClient.newBuilder().addInterceptor(tokenAuthInterceptor).build()
}

fun provideRetrofitStudentApi(okHttpClient: OkHttpClient) : Retrofit{
    return Retrofit
        .Builder()
        .baseUrl(BuildConfig.STUDENT_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideRetrofitRuzApi(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(BuildConfig.RUZ_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideStudentApi(retrofit: Retrofit) : StudentApi = retrofit.create(StudentApi::class.java)

fun provideRuzApi(retrofit: Retrofit) : RuzApi = retrofit.create(RuzApi::class.java)

fun provideRuzDataSource(ruzApi: RuzApi) : RuzDataSource = RuzDataSource(ruzApi)

fun provideRuzRepository(ruzDataSource: RuzDataSource) : RuzRepository = RuzRepositoryImpl(ruzDataSource)