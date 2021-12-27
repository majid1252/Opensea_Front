package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class StatsContainer(
        @field:SerializedName("stats")
        val stats: Stats? = null
)
