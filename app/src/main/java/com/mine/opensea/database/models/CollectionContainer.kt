package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class CollectionContainer(

        @field:SerializedName("collection")
        val collection: Collection? = null,

        )
