package com.dicoding.mydompetku.core.data.source.remote.network

import com.dicoding.mydompetku.core.data.source.remote.response.CashflowResponse
import com.dicoding.mydompetku.core.data.source.remote.response.CategoryResponse
import retrofit2.http.GET

interface ApiService {

    @GET("35af27d1-1bae-4a75-9196-2d4748956576")
    suspend fun getAllCategory(): CategoryResponse

    @GET("de60b98f-2ae4-470a-860a-d2fcc195a2e1")
    suspend fun getAllCashflow(): CashflowResponse
}