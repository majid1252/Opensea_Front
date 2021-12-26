package com.mine.opensea.database.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Stats(

        @field:SerializedName("num_reports")
        val numReports: Int? = null,

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
        val totalSupply: Int? = null,

        @field:SerializedName("seven_day_sales")
        val sevenDaySales: Int? = null,

        @field:SerializedName("count")
        val count: Int? = null,

        @field:SerializedName("average_price")
        val averagePrice: Double? = null,

        @field:SerializedName("one_day_sales")
        val oneDaySales: Int? = null,

        @field:SerializedName("seven_day_change")
        val sevenDayChange: Double? = null,

        @field:SerializedName("thirty_day_sales")
        val thirtyDaySales: Int? = null,

        @field:SerializedName("seven_day_volume")
        val sevenDayVolume: Double? = null,

        @field:SerializedName("one_day_change")
        val oneDayChange: Double? = null,

        @field:SerializedName("market_cap")
        val marketCap: Double? = null,

        @field:SerializedName("thirty_day_change")
        val thirtyDayChange: Double? = null,

        @field:SerializedName("num_owners")
        val numOwners: Int? = null,

        @field:SerializedName("total_sales")
        val totalSales: Int? = null,

        @field:SerializedName("floor_price")
        val floorPrice: Int? = null
)