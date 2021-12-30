package com.mine.opensea.networking

import android.util.Log
import com.mine.opensea.networking.api.OpenseaRetroService
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit


class RestTest {

    lateinit var retrofit: OpenseaRetroService

    @Before
    fun setUp() {
        retrofit = OpenseaRetroService.create()
    }

    @Test
    fun get_collection_api_test() {
        retrofit.getCollections()
            .subscribeOn(Schedulers.io())
            .share()
            .doOnNext {

            }
            .doOnError { throwable ->
                println("getCollections error::" + (throwable as HttpException).message)
            }
            .subscribe()
    }

    @Test
    fun get_single_asset_test() {
        retrofit.getAsset("0xb47e3cd837ddf8e4c57f05d70ab865de6e193bbb", 1)
            .subscribeOn(Schedulers.io())
            .share()
            .doOnNext {
                println(it)
            }
            .doOnError { throwable ->
                println("getAsset error::" + (throwable as HttpException).message)
            }
            .subscribe()
    }

}