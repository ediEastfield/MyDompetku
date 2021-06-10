package com.dicoding.mydompetku.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CashflowResponse(

    @field:SerializedName("error")
    val error: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("results")
    val results: List<ResultItemCashflowResponse>
)
