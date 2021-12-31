package com.mine.opensea.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mine.opensea.database.models.Asset
import com.mine.opensea.database.models.Collection
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface AssetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAssets(collections: List<Asset>): Completable

    @Query("SELECT * from assets_table")
    fun getAssets(): Observable<List<Asset>>
}