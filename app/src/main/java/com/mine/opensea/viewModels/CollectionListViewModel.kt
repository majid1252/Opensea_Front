package com.mine.opensea.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.models.Collection
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CollectionListViewModel @Inject constructor(
        private val collectionDao: CollectionDao,
        private val openseaRetroService: OpenseaRetroService
) : ViewModel() {

    val collectionsModel = MutableLiveData<List<Collection>>()

    init {
        openseaRetroService.getCollections(0, 20)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { it ->
                Log.d("collect_size", it.collections[15].slug.toString())
                collectionDao.insertCollections(it.collections)
            }
            .andThen(
                collectionDao.getCollections()
            )
            .share()
            .doOnNext { it ->
                Log.d("collect_size", it.size.toString())
                collectionsModel.postValue(it)
            }
            .doOnError { throwable -> println(throwable.message) }
            .subscribe()


    }
}