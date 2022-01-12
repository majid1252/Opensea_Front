package com.mine.opensea.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.repos.CollectionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
        private val collectionsRepository: CollectionsRepository
) : ViewModel() {

    private val collectionsPagedLiveData: MutableLiveData<PagingData<Collection>> by lazy {
        MutableLiveData<PagingData<Collection>>().also {
            loadCollections()
        }
    }

    public fun getCollections(): MutableLiveData<PagingData<Collection>> {
        return collectionsPagedLiveData
    }

    private fun loadCollections() {
        collectionsRepository.getCollections().cachedIn(viewModelScope).subscribe { pagesData ->
            collectionsPagedLiveData.postValue(pagesData)
        }
    }
}