package com.mine.opensea.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.models.Response
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.internal.functions.Functions
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CollectionListViewModel @Inject constructor(
        private val collectionDao: CollectionDao,
        private val openseaRetroService: OpenseaRetroService
) : ViewModel() {

    val collectionsModel = MutableLiveData<List<Collection>>()

    init {

        //        openseaRetroService.getCollections()
        //            .subscribeOn(Schedulers.io())
        //            .flatMapCompletable { it ->
        //                Log.d("collect_size", it.collections[15].slug.toString())
        //                collectionDao.insertCollections(it.collections)
        //            }
        //            .andThen(
        //                collectionDao.getCollections()
        //            )
        //            .share()
        //            .doOnNext { it ->
        //                Log.d("collect_size", it.size.toString())
        //                collectionsModel.postValue(it)
        //            }
        //            .doOnError { throwable ->
        //                println("getCollections error::" + (throwable as HttpException).message)
        //            }
        //            .subscribe()

        //        openseaRetroService.getCollection("doodles-official")
        //            .subscribeOn(Schedulers.io())
        //            .share()
        //            .doOnNext { it ->
        //                Log.d("collect_size", it.collection.toString())
        //            }
        //            .doOnError { throwable ->
        //                println("getCollection error::" + throwable.message)
        //            }
        //            .subscribe()
        //
        openseaRetroService.getBundles()
            .subscribeOn(Schedulers.io())
            .share()
            .doOnNext { it ->
                Log.d("collect_size", it.bundles.toString())
            }
            .doOnError { throwable ->
                println("getCollection error::" + throwable.message)
            }
            .subscribe()

    }

}
