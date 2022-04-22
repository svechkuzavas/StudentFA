package com.fa.studentfu.data

import com.fa.studentfu.data.models.AuthorizationModel
import com.fa.studentfu.data.models.ProfileModel
import com.fa.studentfu.data.models.UserApiModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentApi {
    @POST("api-token-auth/")
    suspend fun loginUser(@Body credentials: AuthorizationModel.UserCredentials): Response<AuthorizationModel.AuthorizationToken>

    @POST("register/")
    suspend fun registerUser(registrationData: AuthorizationModel.Registration): Response<AuthorizationModel.Registration>

    @GET("user_profile/{id}/get_full_data/")
    suspend fun fetchUserProfile(@Path("id") id : String) : Response<ProfileModel>
}

/*

1. цель задачи основное предназначение прилы
2. подходы к разработке: описание принципов арх-ры, паттернов
 2.1 чистая архитектура, слои приложения
 2.2 паттерны ui-слоя
3. структура приложения
 3.1 диаграмма архитектуры
 3.2 структура проекта
 3.3 описание функционала
 3.4 пару слов про сетевое взаимодействие
 3.5 пару слов про асинхронность (корутины)
4. Достигнутые результаты
 */