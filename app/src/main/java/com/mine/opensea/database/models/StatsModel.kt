package com.mine.opensea.database.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
class StatsModel(

    @field:SerializedName("num_reports")
    val numReports: Int? = null,

    @field:SerializedName("one_day_average_price")
    val oneDayAveragePrice: Int? = null,

    @field:SerializedName("seven_day_average_price")
    val sevenDayAveragePrice: Int? = null,

    @field:SerializedName("thirty_day_volume")
    val thirtyDayVolume: Int? = null,

    @field:SerializedName("total_volume")
    val totalVolume: Int? = null,

    @field:SerializedName("thirty_day_average_price")
    val thirtyDayAveragePrice: Int? = null,

    @field:SerializedName("one_day_volume")
    val oneDayVolume: Int? = null,

    @field:SerializedName("total_supply")
    val totalSupply: Int? = null,

    @field:SerializedName("seven_day_sales")
    val sevenDaySales: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("average_price")
    val averagePrice: Int? = null,

    @field:SerializedName("one_day_sales")
    val oneDaySales: Int? = null,

    @field:SerializedName("seven_day_change")
    val sevenDayChange: Int? = null,

    @field:SerializedName("thirty_day_sales")
    val thirtyDaySales: Int? = null,

    @field:SerializedName("seven_day_volume")
    val sevenDayVolume: Int? = null,

    @field:SerializedName("one_day_change")
    val oneDayChange: Int? = null,

    @field:SerializedName("market_cap")
    val marketCap: Int? = null,

    @field:SerializedName("thirty_day_change")
    val thirtyDayChange: Int? = null,

    @field:SerializedName("num_owners")
    val numOwners: Int? = null,

    @field:SerializedName("total_sales")
    val totalSales: Int? = null,

    @field:SerializedName("floor_price")
    val floorPrice: Int? = null
)