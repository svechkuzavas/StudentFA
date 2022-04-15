package com.fa.studentfu.core.data

import java.lang.Exception

class NoInternetConnectionException : Exception() {
    override val message: String
        get() = "You are offline"
}