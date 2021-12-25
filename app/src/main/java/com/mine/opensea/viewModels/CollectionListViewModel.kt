package com.mine.opensea.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.models.CollectionModel
import com.mine.opensea.database.models.CollectionsModel
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CollectionListViewModel @Inject constructor(
        private val collectionDao: CollectionDao,
        private val openseaRetroService: OpenseaRetroService
) : ViewModel() {

    val collectionsModel = MutableLiveData<List<CollectionModel>>()

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