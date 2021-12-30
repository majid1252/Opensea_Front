package com.mine.opensea.database.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Stats(

        @field:SerializedName("num_reports")
        val numReports: Double? = null,

        @field:SerializedName("one_day_average_price")
        val oneDayAveragePrice: Double? = null,

        @field:SerializedName("seven_day_average_price")
        val sevenDayAveragePrice: Double? = null,

        @field:SerializedName("thirty_day_volume")
        val thirtyDayVolume: Double? = null,

        @field:SerializedName("total_volume")
        val totalVolume: Double? = null,

        @field:SerializedName("thirty_day_average_price")
        val thirtyDayAveragePrice: Double? = null,

        @field:SerializedName("one_day_volume")
        val oneDayVolume: Double? = null,

        @field:SerializedName("total_supply")
        val totalSupply: Double? = null,

        @field:SerializedName("seven_day_sales")
        val sevenDaySales: Double? = null,

        @field:SerializedName("count")
        val count: Double? = null,

        @field:SerializedName("average_price")
        val averagePrice: Double? = null,

        @field:SerializedName("one_day_sales")
        val oneDaySales: Double? = null,

        @field:SerializedName("seven_day_change")
        val sevenDayChange: Double? = null,

        @field:SerializedName("thirty_day_sales")
        val thirtyDaySales: Double? = null,

        @field:SerializedName("seven_day_volume")
        val sevenDayVolume: Double? = null,

        @field:SerializedName("one_day_change")
        val oneDayChange: Double? = null,

        @field:SerializedName("market_cap")
        val marketCap: Double? = null,

        @field:SerializedName("thirty_day_change")
        val thirtyDayChange: Double? = null,

        @field:SerializedName("num_owners")
        val numOwners: Double? = null,

        @field:SerializedName("total_sales")
        val totalSales: Double? = null,

        @field:SerializedName("floor_price")
        val floorPrice: Double? = null
)