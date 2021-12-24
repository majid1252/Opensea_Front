package com.mine.opensea.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.models.CollectionsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionListViewModel @Inject constructor(private val collectionDao: CollectionDao) :
    ViewModel() {

    val collectionsModel: LiveData<CollectionsModel> = LiveData<CollectionsModel>()

}