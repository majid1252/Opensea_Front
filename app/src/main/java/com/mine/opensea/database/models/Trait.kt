package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class Trait(
        @field:SerializedName("display_type")
        val displayType: Any? = null,

        @field:SerializedName("trait_count")
        val traitCount: Int? = null,

        @field:SerializedName("value")
        val value: String? = null,

        @field:SerializedName("trait_type")
        val traitType: String? = null,

        @field:SerializedName("max_value")
        val maxValue: Any? = null,

        @field:SerializedName("order")
        val order: Any? = null
)
