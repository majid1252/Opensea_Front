package com.mine.opensea.networking.api

import com.mine.opensea.networking.CollectionJsonConvertor
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.google.gson.Gson

import com.google.gson.GsonBuilder
import com.mine.opensea.database.models.*
import com.mine.opensea.database.models.Collection


interface OpenseaRetroService {

    /**
     * these are all opensea.io methods for retrieving Collections, Assets and Stats
     */
    @GET("/collections")
    fun getCollections(
            @Query("offset") offset: Int,
            @Query("asset_owner") assetOwner: Int,
            @Query("limit") limit: Int
    ): Observable<Collections>


    /**
     * To retrieve assets from our API, call the /assets endpoint with the desired filter parameters.
     */
    @GET("/collections")
    fun getAssets(
            @Query("offset") offset: Int = 0,
            @Query("limit") limit: Int = 10,
            @Query("collection") collectionSlug: String = "",
            @Query("owner") owner: String = "",
            @Query("order_by") orderBy: String = "",
            @Query("order_direction") orderDirection: String = "",
    ): Observable<List<Asset>>


    /**
     * Used to fetch more in-depth information about an individual asset
     */
    @GET("/asset/{asset_contract_address}/{token_id}/")
    fun getAsset(
            @Path("asset_contract_address") assetContractAddress: String,
            @Path("token_id") tokenId: Int
    ): Observable<Asset>


    /**
     * Used to fetch more in-depth information about an contract asset
     */
    @GET("/asset_contract/{asset_contract_address}/")
    fun getAssetByContract(
            @Path("asset_contract_address") assetContractAddress: String
    ): Observable<AssetContract>


    /**
     * Used for retrieving more in-depth information about individual collections including real time statistics like floor price
     */
    @GET("/collection/{collection_slug}/")
    fun getCollection(
            @Path("collection_slug") assetContractAddress: String
    ): Observable<CollectionContainer>


    /**
     * Use this endpoint to fetch stats for a specific collection, including realtime floor price statistics
     */
    @GET("collection/{collection_slug}/stats")
    fun getCollectionStats(
            @Path("collection_slug") assetContractAddress: String
    ): Observable<StatsContainer>


    /**
     * [OpenseaRetroService] is gonna get injected where it is needed using Hilt.
     */
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