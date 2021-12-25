package com.mine.opensea.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CollectionsModel(

    @field:SerializedName("collections")
    val collections: List<CollectionModel>

)


