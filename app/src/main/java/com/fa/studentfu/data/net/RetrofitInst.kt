package com.fa.studentfu.data.net

import com.fa.studentfu.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed class RetrofitInst{

    class Ruz(client: OkHttpClient) : RetrofitInst() {
        val retrofit =  Retrofit.Builder()
            .baseUrl(BuildConfig.RUZ_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    class Student(client: OkHttpClient) : RetrofitInst() {
        val retrofit =  Retrofit.Builder()
            .baseUrl(BuildConfig.STUDENT_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
