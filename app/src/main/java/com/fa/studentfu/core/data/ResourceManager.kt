package com.fa.studentfu.core.data

interface Resource {
    interface StringManager {
        fun getString(key : String) : String
        fun saveString(key: String, value: String)
    }
    interface UserData {
        fun getToken() : String
        fun getProfileId() : String
        fun getUserId() : String
        fun saveToken(token: String)
        fun saveId(id : String)
        fun saveUserId(id : String)
        fun clearUserData()
    }
}