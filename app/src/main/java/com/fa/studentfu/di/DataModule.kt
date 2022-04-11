package com.fa.studentfu.di

import com.fa.studentfu.BuildConfig
import com.fa.studentfu.data.RuzApi
import com.fa.studentfu.data.StudentApi
import com.fa.studentfu.data.net.TokenAuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory { TokenAuthInterceptor(get()) }
    factory { provideOkHttpClientTokenAuthorization(get()) }

    single { provideRetrofitStudentApi(get()) }
    single { provideRetrofitRuzApi()}

    factory { provideStudentApi(get()) }
    factory { provideRuzApi(get()) }
}

fun provideOkHttpClientTokenAuthorization(tokenAuthInterceptor : TokenAuthInterceptor) : OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(tokenAuthInterceptor)
        .build()
}

fun provideRetrofitStudentApi(okHttpClient: OkHttpClient) : Retrofit{
    return Retrofit
        .Builder()
        .baseUrl(BuildConfig.STUDENT_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideRetrofitRuzApi(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BuildConfig.RUZ_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideStudentApi(retrofit: Retrofit) : StudentApi = retrofit.create(StudentApi::class.java)

fun provideRuzApi(retrofit: Retrofit) : RuzApi = retrofit.create(RuzApi::class.java)
