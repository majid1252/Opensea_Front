package com.mine.opensea.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mine.opensea.database.models.Bundle
import com.mine.opensea.database.models.Collection
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface BundlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBundles(collections: List<Bundle>): Completable

    @Query("SELECT * from bundles_table")
    fun getBundles(): Observable<List<Bundle>>
}