package com.dicoding.mydompetku.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultsCategoryResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("icon")
    val icon: String,

    @field:SerializedName("jenis")
    val jenis: String
)
