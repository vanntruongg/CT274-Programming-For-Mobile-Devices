package com.example.b2012051_tranvantruong_uocmo.constants

import com.example.b2012051_tranvantruong_uocmo.apis.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ObjectConstants {
    companion object {
        private const val BASE_URL = "https://bestwishesserver-production.up.railway.app/api/"
        private const val BASE_URL_BACKUP = "http://bestwishes-ct274.vercel.app/api/"

        fun getInstance(): ApiService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
        }
    }
}