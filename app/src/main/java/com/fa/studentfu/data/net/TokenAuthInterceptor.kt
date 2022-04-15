package com.fa.studentfu.data.net

import com.fa.studentfu.core.data.Resource
import okhttp3.Interceptor
import okhttp3.Response

class TokenAuthInterceptor(private val tokenManager : Resource.TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run{
        proceed(
            request()
                .newBuilder()
                .addHeader(tokenManager.getHeader(), tokenManager.getToken())
                .build()
        )
    }
}