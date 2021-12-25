package com.mine.opensea.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mine.opensea.database.models.CollectionModel
import com.mine.opensea.database.models.CollectionsModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollections(collections: List<CollectionModel>): Completable

    @Query("SELECT * from collections_table")
    fun getCollections(): Observable<List<CollectionModel>>
}