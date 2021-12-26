package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class CollectionsModel(

        @field:SerializedName("collections")
        val collections: List<Collection>

)


