package com.mine.opensea.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.models.StatsContainer
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionDetailsViewModel @Inject constructor(
        private val openseaRetroService: OpenseaRetroService
) : ViewModel() {

    private val collectionLiveData = MutableLiveData<Collection?>()
    private val collectionStatsLiveData = MutableLiveData<StatsContainer?>()

    fun getCollectionDetails(collectionSlug: String): MutableLiveData<Collection?> {
        openseaRetroService.getCollection(collectionSlug)
            .subscribe({ collectionLiveData.postValue(it.collection) }, {})
        return collectionLiveData
    }

    fun getCollectionStats(collectionSlug: String): MutableLiveData<StatsContainer?> {
        openseaRetroService.getCollectionStats(collectionSlug)
            .subscribe({ collectionStatsLiveData.postValue(it) }, {})
        return collectionStatsLiveData
    }
}