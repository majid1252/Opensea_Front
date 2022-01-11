package com.mine.opensea.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mine.opensea.database.models.Collections

@Dao
interface CollectionRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeys(remoteKey: List<Collections.CollectionRemoteKeys>)

    @Query("SELECT * FROM collections_remote_keys WHERE id = :id")
    fun remoteKeyByPK(id: Long): Collections.CollectionRemoteKeys?

    @Query("DELETE FROM collections_remote_keys")
    fun clearAllKeys()

}