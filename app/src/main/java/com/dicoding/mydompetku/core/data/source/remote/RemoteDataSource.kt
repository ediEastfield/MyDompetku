package com.dicoding.mydompetku.core.data.source.remote

import android.util.Log
import com.dicoding.mydompetku.core.data.source.remote.network.ApiResponse
import com.dicoding.mydompetku.core.data.source.remote.network.ApiService
import com.dicoding.mydompetku.core.data.source.remote.response.ResultsCategoryResponse
import com.dicoding.mydompetku.core.data.source.remote.response.ResultItemCashflowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllCategory(): Flow<ApiResponse<List<ResultsCategoryResponse>>> {

        return flow {
            try {
                val response = apiService.getAllCategory()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllCashflow(): Flow<ApiResponse<List<ResultItemCashflowResponse>>> {

        return flow {
            try {
                val response = apiService.getAllCashflow()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}