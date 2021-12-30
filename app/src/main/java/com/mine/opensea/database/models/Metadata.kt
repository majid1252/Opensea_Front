package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class Metadata(

        @field:SerializedName("bundle")
        val bundle: Bundle? = null
)