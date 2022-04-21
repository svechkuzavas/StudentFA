package com.fa.studentfu.core.data

import java.lang.Exception

class NoInternetException : Exception() {
    override val message: String
        get() = "Отсутсвует интернет"
}