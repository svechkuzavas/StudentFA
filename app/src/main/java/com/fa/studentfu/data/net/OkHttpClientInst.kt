package com.fa.studentfu.data.net

import com.fa.studentfu.core.interceptors.BaseInterceptor
import com.fa.studentfu.core.interceptors.TokenAuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import kotlin.math.log

sealed class OkHttpClientInst {
    // Базовая реализация клиента с логированием запросов и
    // проверкой сетевого подключения
    class Base(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptorBase: BaseInterceptor): OkHttpClientInst(){
        val client: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(interceptorBase)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    // Реализация клиента с функционалом базового и
    // аутентификацией по токену
    class TokenAuth(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptorBase: BaseInterceptor,
        interceptorAuth: TokenAuthInterceptor
    ): OkHttpClientInst(){
        val client: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(interceptorBase)
            .addInterceptor(interceptorAuth)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}