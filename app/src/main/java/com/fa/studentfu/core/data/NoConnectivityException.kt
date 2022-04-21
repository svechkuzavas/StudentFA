package com.fa.studentfu.core.data

import java.lang.Exception

class NoConnectivityException : Exception() {
    override val message: String
        get() = "Нет доступных подключений"
}