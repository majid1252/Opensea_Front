package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class Transaction(

        @field:SerializedName("from_account")
        val fromAccount: User? = null,

        @field:SerializedName("block_hash")
        val blockHash: String? = null,

        @field:SerializedName("block_number")
        val blockNumber: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("transaction_index")
        val transactionIndex: String? = null,

        @field:SerializedName("transaction_hash")
        val transactionHash: String? = null,

        @field:SerializedName("timestamp")
        val timestamp: String? = null,

        @field:SerializedName("to_account")
        val toAccount: ToAccount? = null
)