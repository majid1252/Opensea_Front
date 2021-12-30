package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class BundlesContainer(
        @field:SerializedName("bundles")
        val bundles: List<Bundle?>?
)
