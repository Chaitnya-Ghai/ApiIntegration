package com.example.apiintegration

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("*/api/user")
    suspend fun getData(): Response<ResponseModel>
}