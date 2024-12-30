package com.example.apiintegration

import com.example.apiintegration.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {
    companion object{
        private val retrofit by lazy{
//            lazy means we only initialize this here once
            val logging=HttpLoggingInterceptor()
//            attach to retrofit object
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//            network client
            val client = OkHttpClient.Builder().addInterceptor(logging).build()
//            pass the client to retrofit instance
            Retrofit.Builder()
                .baseUrl(BASE_URL)
//                addConverterFactory is used to determine how the response should be interpreted and converted to kotlin objects
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
//        GET API INSTANCE FROM RETROFIT BUILDER
//        api object
//        this can be used from everywhere to make network requests
        val api by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }
}