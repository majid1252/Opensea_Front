package com.mine.opensea.database.repos

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.mine.opensea.OpenseaApplication
import com.mine.opensea.database.OpenseaDatabase
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.models.Collections
import com.mine.opensea.networking.api.OpenseaRetroService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InvalidObjectException
import javax.inject.Inject

@ExperimentalPagingApi
class CollectionsRemoteMediator() : RxRemoteMediator<Int, Collection>() {

    //TODO() reorganizing architecture specifically for Injections
    val database: OpenseaDatabase = OpenseaDatabase.getInstance(OpenseaApplication.context)

    val openseaRetroService: OpenseaRetroService = OpenseaRetroService.create()

    companion object {
        const val INVALID_PAGE = -1
    }

    override fun loadSingle(
            loadType: LoadType,
            state: PagingState<Int, Collection>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                Log.d("page:", it.toString())
                when (it) {
                    null, LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextKey?.minus(1) ?: 1
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                            ?: throw InvalidObjectException("Result is empty")
                        remoteKeys.prevKey ?: INVALID_PAGE
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                            ?: throw InvalidObjectException("Result is empty")
                        remoteKeys.nextKey ?: INVALID_PAGE
                    }
                }
            }
            .flatMap { page ->
                Log.d("page:", page.toString())
                if (page == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    openseaRetroService.getCollections(offset = (page - 1) * 20, limit = 20)
                        .map { insertToDb(page, loadType, it) }
                        .map<MediatorResult> { MediatorResult.Success(endOfPaginationReached = false) }
                        .onErrorReturn { MediatorResult.Error(it) }
                }
            }
            .onErrorReturn { MediatorResult.Error(it) }
    }


    private fun insertToDb(page: Int, loadType: LoadType, data: Collections): Collections {

        if (loadType == LoadType.REFRESH) {
            database.collectionsRemoteKeys().clearAllKeys()
            database.collectionDao().clearAllCollections()
        }

        val prevKey = if (page == 1) null else page - 1
        val nextKey = if (data.endOfPage) null else page + 1

        //these are ordered such way so that Room can set auto generated Primary keys
        database.collectionDao().insertCollections(data.collections)
        val keys = database.collectionDao().getCollectionsByList().map {
            Collections.CollectionRemoteKeys(
                id = it.id,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }
        database.collectionsRemoteKeys().insertKeys(keys)

        return data
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, Collection>): Collections.CollectionRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { repo ->
            database.collectionsRemoteKeys().remoteKeyByPK(repo.id)
        }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, Collection>): Collections.CollectionRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { collection ->
                database.collectionsRemoteKeys().remoteKeyByPK(collection.id)
            }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Collection>): Collections.CollectionRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.collectionsRemoteKeys().remoteKeyByPK(id)
            }
        }
    }
}