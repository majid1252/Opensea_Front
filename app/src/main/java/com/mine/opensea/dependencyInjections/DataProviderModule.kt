package com.mine.opensea.dependencyInjections

import android.content.Context
import com.mine.opensea.database.OpenseaDatabase
import com.mine.opensea.database.daos.CollectionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
}