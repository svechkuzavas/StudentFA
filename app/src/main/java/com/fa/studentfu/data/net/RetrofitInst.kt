package com.fa.studentfu.data.net

import com.fa.studentfu.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed class RetrofitInst{
    // Класс для подключения к API расписания
    class Ruz(client: OkHttpClient) : RetrofitInst() {
        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl(BuildConfig.RUZ_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Класс для подключения к основному API
    class Student(client: OkHttpClient) : RetrofitInst() {
        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl(BuildConfig.STUDENT_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
