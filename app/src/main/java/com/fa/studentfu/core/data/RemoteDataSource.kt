package com.fa.studentfu.core.data

interface RemoteDataSource<T> {

    suspend fun fetch() : T

    suspend fun fetchList() : List<T>

    suspend fun send(instance : T)

}