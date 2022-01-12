package com.mine.opensea.dependencyInjections

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.mine.opensea.database.OpenseaDatabase
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.daos.CollectionRemoteKeysDao
import com.mine.opensea.database.repos.CollectionsRemoteMediator
import com.mine.opensea.networking.api.OpenseaRetroService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@InstallIn(SingletonComponent::class)
@Module
class DataProviderModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): OpenseaDatabase {
        return OpenseaDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCollectionDao(@ApplicationContext context: Context): CollectionDao {
        return OpenseaDatabase.getInstance(context).collectionDao()
    }

    @Singleton
    @Provides
    fun provideCollectionsRemoteKeyDao(@ApplicationContext context: Context): CollectionRemoteKeysDao {
        return OpenseaDatabase.getInstance(context).collectionsRemoteKeys()
    }

}
