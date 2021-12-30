package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class DisplayData(

        @field:SerializedName("card_display_style")
        val cardDisplayStyle: String? = null
)