package com.mine.opensea.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.repos.CollectionsRepository
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
@HiltViewModel
class CollectionsFragmentViewModel @Inject constructor(
        private val collectionDao: CollectionDao,
        private val openseaRetroService: OpenseaRetroService
) : ViewModel() {
    val collectionsPagingLive = MutableLiveData<PagingData<Collection>>()

    init {
        //        openseaRetroService.getCollections()
        //            .subscribeOn(Schedulers.io())
        //            .doOnError {
        //                Log.d("observe::", "retrofit error")
        //            }
        //            .retryWhen { it.delay(5, TimeUnit.SECONDS) }
        //            .flatMapCompletable { it ->
        //                Log.d("observe::", "flatMap completable")
        //                collectionDao.insertCollections(it.collections)
        //            }
        //            .andThen(
        //                collectionDao.getCollections()
        //            )
        //            .doOnNext {
        //                Log.d("observe::", "get from database")
        //            }
        //            .share()
        //            .subscribe(
        //                { collectionsModel.postValue(it) },
        //                { e ->
        //                    Log.d("observe::", "subscribe error")
        //                }
        //            )
        CollectionsRepository().getCollections().cachedIn(viewModelScope).subscribe { pagesData ->
            collectionsPagingLive.postValue(pagesData)
        }


    }

}