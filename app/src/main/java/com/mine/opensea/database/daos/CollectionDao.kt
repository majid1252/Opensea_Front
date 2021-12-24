package com.mine.opensea.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.models.CollectionsModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCollections(books: List<Collection>): Completable

    @Query("SELECT * from collectionsmodel")
    fun getCollections(): Flow<CollectionsModel>
}