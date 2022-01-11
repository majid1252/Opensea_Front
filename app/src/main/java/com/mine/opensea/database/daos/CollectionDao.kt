package com.mine.opensea.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mine.opensea.database.models.Collection
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollections(collections: List<Collection>)

    @Query("SELECT * from collections_table")
    fun getCollections(): PagingSource<Int, Collection>

    @Query("SELECT * from collections_table")
    fun getCollectionsByList(): List<Collection>

    @Query("DELETE FROM collections_table")
    fun clearAllCollections()
}