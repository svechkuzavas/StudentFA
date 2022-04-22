package com.fa.studentfu.data.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.fa.studentfu.core.data.Resource

class SharedPrefTokenStorage(context : Context) : Resource.UserData {

    private val sharedPreference : SharedPreferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)

    override fun getToken(): String =
        sharedPreference.getString(TOKEN_KEY, TOKEN_DEFAULT_VALUE) ?: TOKEN_DEFAULT_VALUE


    override fun getProfileId(): String =
        sharedPreference.getString(PROFILE_ID_KEY, ID_DEFAULT_VALUE) ?: ID_DEFAULT_VALUE

    override fun getUserId(): String =
        sharedPreference.getString(USER_ID_KEY, ID_DEFAULT_VALUE) ?: ID_DEFAULT_VALUE

    override fun saveToken(token: String) {
        sharedPreference.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun saveId(id: String) {
        sharedPreference.edit().putString(PROFILE_ID_KEY, id).apply()
    }

    override fun saveUserId(id: String) {
        sharedPreference.edit().putString(USER_ID_KEY, id).apply()
    }

    override fun clearUserData() {
        sharedPreference.edit().remove(TOKEN_KEY).remove(PROFILE_ID_KEY).remove(USER_ID_KEY).apply()
    }

    companion object {
        const val STORAGE_NAME = "STORAGE_USER_DATA"
        const val TOKEN_KEY = "TOKEN"
        const val HEADER_KEY = "HEADER"
        const val PROFILE_ID_KEY = "PROFILE_ID"
        const val USER_ID_KEY = "USER_ID"
        const val HEADER_DEFAULT_VALUE = "Authorization"
        const val TOKEN_DEFAULT_VALUE = ""
        const val ID_DEFAULT_VALUE = "0"
    }
}