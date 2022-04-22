package com.fa.studentfu.data.repo

import com.fa.studentfu.core.data.Resource
import com.fa.studentfu.domain.repo.UserDataRepository

class UserDataRepositoryImpl(private val dataSource: Resource.UserData) : UserDataRepository {
    override fun saveToken(token: String) {
        dataSource.saveToken(token)
    }

    override fun saveId(id: String) {
        dataSource.saveId(id)
    }

    override fun saveUserId(id: String) {
        dataSource.saveUserId(id)
    }

    override fun getToken(): String = dataSource.getToken()

    override fun getProfileId(): String = dataSource.getProfileId()

    override fun getUserId(): String = dataSource.getUserId()

    override fun clearUserData() {
        dataSource.clearUserData()
    }
}