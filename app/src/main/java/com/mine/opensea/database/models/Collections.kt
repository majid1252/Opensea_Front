package com.mine.opensea.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

data class Collections(
        var total: Int = 0,
        var page: Int = 0,
        val collections: List<Collection>

) {
    @IgnoredOnParcel
    val endOfPage: Boolean = total == page

    @Parcelize
    @Entity(tableName = "collections_remote_keys")
    data class CollectionRemoteKeys(
            @PrimaryKey val id: Long,
            val prevKey: Int?,
            val nextKey: Int?
    ) : Parcelable
}


