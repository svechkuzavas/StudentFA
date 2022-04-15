package com.fa.studentfu.core.data

interface Resource {
    interface StringManager {
        fun getString(key : String) : String
        fun saveString(key: String, value: String)
    }
    interface TokenManager {
        fun getHeader() : String
        fun getToken() : String
        fun saveHeader(header : String)
        fun saveToken(token: String)
    }
}