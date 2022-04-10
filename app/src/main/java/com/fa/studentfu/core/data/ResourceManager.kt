package com.fa.studentfu.core.data

interface ResourceManager {

    interface TokenManager {
        fun getHeader() : String
        fun getToken() : String
    }
}