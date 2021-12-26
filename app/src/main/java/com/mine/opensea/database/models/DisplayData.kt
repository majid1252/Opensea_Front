package com.mine.opensea.database.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class DisplayData(

        @field:SerializedName("images")
        val images: List<String?>? = null,

        @field:SerializedName("card_display_style")
        val cardDisplayStyle: String? = null
)

