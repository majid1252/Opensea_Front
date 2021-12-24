package com.mine.opensea.database.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class CollectionsModel(

    @field:SerializedName("collections")
    val collections: List<CollectionModel>

)


