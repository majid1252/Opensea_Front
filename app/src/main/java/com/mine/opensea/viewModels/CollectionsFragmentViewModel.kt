package com.mine.opensea.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.models.Collection
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CollectionsFragmentViewModel @Inject constructor(
        private val collectionDao: CollectionDao,
        private val openseaRetroService: OpenseaRetroService
) : ViewModel() {
    val collectionsModel = MutableLiveData<List<Collection>>()

    init {
        openseaRetroService.getCollections()
            .subscribeOn(Schedulers.io())
            .doOnError {
                Log.d("observe::", "retrofit error")
            }
            .retryWhen { it.delay(5, TimeUnit.SECONDS) }
            .flatMapCompletable { it ->
                Log.d("observe::", "flatMap completable")
                collectionDao.insertCollections(it.collections)
            }
            .andThen(
                collectionDao.getCollections()
            )
            .doOnNext {
                Log.d("observe::", "get from database")
            }
            .share()
            .subscribe(
                { collectionsModel.postValue(it) },
                { e ->
                    Log.d("observe::", "subscribe error")
                }
            )
    }
}