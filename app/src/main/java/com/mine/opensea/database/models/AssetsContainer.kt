package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class AssetsContainer(
        @field:SerializedName("assets")
        val assets: List<Asset> = emptyList()
)
