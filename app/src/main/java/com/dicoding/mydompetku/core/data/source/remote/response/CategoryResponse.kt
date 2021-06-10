package com.dicoding.mydompetku.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("results")
    val results: List<ResultsCategoryResponse>
)
