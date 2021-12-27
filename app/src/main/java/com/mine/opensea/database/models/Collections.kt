package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class Collections(

        @field:SerializedName("collections")
        val collections: List<Collection>

) : Response()


