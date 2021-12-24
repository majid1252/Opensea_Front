package com.mine.opensea.database.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class DisplayDataModel(

    @field:SerializedName("images")
    val images: List<Any?>? = null,

    @field:SerializedName("card_display_style")
    val cardDisplayStyle: String? = null
)

