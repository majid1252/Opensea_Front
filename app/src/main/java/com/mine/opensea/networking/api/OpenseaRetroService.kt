package com.mine.opensea.networking.api

import com.mine.opensea.database.models.CollectionsModel
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface OpenseaRetroService {

    /**
     * these are all opensea.io methods for retrieving Collections, Assets and Stats
     * nothing more
     */

    @GET("/collections")
    fun getCollections(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int
    ): Observable<CollectionsModel>

    companion object {
        private const val BASE_URL = "https://api.opensea.io/api/v1/"

        fun create(): OpenseaRetroService {

            val client = OkHttpClient.Builder().build()

            return Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()).build()
                .create(OpenseaRetroService::class.java)
        }
    }


}