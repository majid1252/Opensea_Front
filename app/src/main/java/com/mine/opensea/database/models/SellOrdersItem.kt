package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class SellOrdersItem(

        @field:SerializedName("closing_date")
        val closingDate: String? = null,

        @field:SerializedName("metadata")
        val metadata: Metadata? = null,

        @field:SerializedName("fee_recipient")
        val feeRecipient: FeeRecipient? = null,

        @field:SerializedName("payment_token")
        val paymentToken: String? = null,

        @field:SerializedName("taker_relayer_fee")
        val takerRelayerFee: String? = null,

        @field:SerializedName("order_hash")
        val orderHash: String? = null,

        @field:SerializedName("taker_protocol_fee")
        val takerProtocolFee: String? = null,

        @field:SerializedName("listing_time")
        val listingTime: Int? = null,

        @field:SerializedName("extra")
        val extra: String? = null,

        @field:SerializedName("base_price")
        val basePrice: String? = null,

        @field:SerializedName("maker_referrer_fee")
        val makerReferrerFee: String? = null,

        @field:SerializedName("replacement_pattern")
        val replacementPattern: String? = null,

        @field:SerializedName("taker")
        val taker: User? = null,

        @field:SerializedName("static_target")
        val staticTarget: String? = null,

        @field:SerializedName("side")
        val side: Int? = null,

        @field:SerializedName("quantity")
        val quantity: String? = null,

        @field:SerializedName("salt")
        val salt: String? = null,

        @field:SerializedName("expiration_time")
        val expirationTime: Int? = null,

        @field:SerializedName("maker")
        val maker: User? = null,

        @field:SerializedName("approved_on_chain")
        val approvedOnChain: Boolean? = null,

        @field:SerializedName("prefixed_hash")
        val prefixedHash: String? = null,

        @field:SerializedName("target")
        val target: String? = null,

        @field:SerializedName("current_bounty")
        val currentBounty: String? = null,

        @field:SerializedName("finalized")
        val finalized: Boolean? = null,

        @field:SerializedName("closing_extendable")
        val closingExtendable: Boolean? = null,

        @field:SerializedName("bounty_multiple")
        val bountyMultiple: String? = null,

        @field:SerializedName("static_extradata")
        val staticExtradata: String? = null,

        @field:SerializedName("payment_token_contract")
        val paymentTokenContract: PaymentTokenContract? = null,

        @field:SerializedName("r")
        val R: String? = null,

        @field:SerializedName("s")
        val S: String? = null,

        @field:SerializedName("fee_method")
        val feeMethod: Int? = null,

        @field:SerializedName("calldata")
        val calldata: String? = null,

        @field:SerializedName("how_to_call")
        val howToCall: Int? = null,

        @field:SerializedName("v")
        val V: Int? = null,

        @field:SerializedName("cancelled")
        val cancelled: Boolean? = null,

        @field:SerializedName("exchange")
        val exchange: String? = null,

        @field:SerializedName("created_date")
        val createdDate: String? = null,

        @field:SerializedName("current_price")
        val currentPrice: String? = null,

        @field:SerializedName("sale_kind")
        val saleKind: Int? = null,

        @field:SerializedName("maker_relayer_fee")
        val makerRelayerFee: String? = null,

        @field:SerializedName("maker_protocol_fee")
        val makerProtocolFee: String? = null,

        @field:SerializedName("marked_invalid")
        val markedInvalid: Boolean? = null
)