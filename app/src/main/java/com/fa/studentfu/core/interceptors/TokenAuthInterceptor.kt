package com.fa.studentfu.core.interceptors

import com.fa.studentfu.core.data.Resource
import okhttp3.Interceptor
import okhttp3.Response

class TokenAuthInterceptor(private val tokenManager : Resource.UserData) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()
        return if (token.isNotEmpty()){
            chain.run{
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(HEADER, "${TOKEN_PREFIX}${tokenManager.getToken()}")
                        .build()
                )
            }
        } else {
            chain.run { proceed(request()) }
        }
    }

    companion object{
        private const val HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Token "
    }
}