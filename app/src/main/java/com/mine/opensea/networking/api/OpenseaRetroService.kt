package com.mine.opensea.networking.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface OpenseaRetroService {

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"

        fun create(): OpenseaRetroService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenseaRetroService::class.java)
        }
    }


}