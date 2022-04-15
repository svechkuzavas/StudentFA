package com.fa.studentfu.data.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.fa.studentfu.core.data.Resource

class SharedPrefDataSource(context : Context) : Resource.TokenManager {

    private val sharedPreference : SharedPreferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)

    override fun getHeader(): String {
        return sharedPreference.getString(HEADER_KEY, HEADER_DEFAULT_VALUE) ?: HEADER_DEFAULT_VALUE
    }

    override fun getToken(): String {
        return sharedPreference.getString(TOKEN_KEY, TOKEN_DEFAULT_VALUE) ?: TOKEN_DEFAULT_VALUE
    }

    override fun saveHeader(header: String) {
        sharedPreference.edit().putString(HEADER_KEY, header).apply()
    }

    override fun saveToken(token: String) {
        sharedPreference.edit().putString(TOKEN_KEY, token).apply()
    }

    companion object {
        const val STORAGE_NAME = "STORAGE_TOKEN"
        const val TOKEN_KEY = "TOKEN"
        const val HEADER_KEY = "HEADER"
        const val HEADER_DEFAULT_VALUE = ""
        const val TOKEN_DEFAULT_VALUE = "Authorization"
    }
}