package com.mine.opensea.database.repos

import android.content.Context
import androidx.paging.*
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.mine.opensea.OpenseaApplication.Companion.context
import com.mine.opensea.database.OpenseaDatabase
import com.mine.opensea.database.models.Collection
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ExperimentalPagingApi
class CollectionsRepository {

    @ExperimentalPagingApi
    fun getCollections(): Flowable<PagingData<Collection>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40
            ),
            remoteMediator = CollectionsRemoteMediator()
        ) {
            OpenseaDatabase.getInstance(context).collectionDao().getCollections()
        }.flowable
    }

}