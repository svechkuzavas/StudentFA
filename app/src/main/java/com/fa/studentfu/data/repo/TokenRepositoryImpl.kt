package com.fa.studentfu.data.repo

import com.fa.studentfu.core.data.Resource
import com.fa.studentfu.domain.repo.TokenRepository

class TokenRepositoryImpl(private val dataSource: Resource.TokenManager) : TokenRepository {
    override fun saveToken(token: String) {
        dataSource.saveToken(token)
    }

    override fun getHeader(): String {
        return dataSource.getHeader()
    }

    override fun getToken(): String {
        return dataSource.getToken()
    }

    override fun saveHeader(header: String) {
        dataSource.saveHeader(header)
    }
}