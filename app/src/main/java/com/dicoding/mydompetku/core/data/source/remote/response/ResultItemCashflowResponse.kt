package com.dicoding.mydompetku.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultItemCashflowResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("icon")
    val icon: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("jenis")
    val jenis: String,

    @field:SerializedName("note")
    val note: String,

    @field:SerializedName("dates")
    val dates: String,

    @field:SerializedName("nominal")
    val nominal: Int
)
