package com.mine.opensea.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mine.opensea.database.models.Collection
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionDetailsViewModel @Inject constructor(
        private val openseaRetroService: OpenseaRetroService
) : ViewModel() {

    private val collectionDetailsLiveData = MutableLiveData<Collection?>()

    fun getCollectionDetails(collectionSlug: String): MutableLiveData<Collection?> {
        openseaRetroService.getCollection(collectionSlug)
            .doOnNext {
                collectionDetailsLiveData.postValue(it.collection)
            }
            .doOnError {

            }
            .subscribe()
        return collectionDetailsLiveData
    }
}