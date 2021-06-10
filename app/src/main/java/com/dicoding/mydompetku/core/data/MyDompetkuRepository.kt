package com.dicoding.mydompetku.core.data

import com.dicoding.mydompetku.core.data.source.local.LocalDataSource
import com.dicoding.mydompetku.core.data.source.remote.RemoteDataSource
import com.dicoding.mydompetku.core.data.source.remote.network.ApiResponse
import com.dicoding.mydompetku.core.data.source.remote.response.ResultItemCashflowResponse
import com.dicoding.mydompetku.core.data.source.remote.response.ResultsCategoryResponse
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.model.Category
import com.dicoding.mydompetku.core.domain.model.Report
import com.dicoding.mydompetku.core.domain.repository.IMyDompetkuRepository
import com.dicoding.mydompetku.core.utils.AppExecutors
import com.dicoding.mydompetku.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyDompetkuRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMyDompetkuRepository {
    
    override fun getAllCategory(jenis: String): Flow<Resource<List<Category>>> =
        object : NetworkBoundResource<List<Category>, List<ResultsCategoryResponse>>() {
            override fun loadFromDB(): Flow<List<Category>> {
                return localDataSource.getAllCategory(jenis).map {
                    DataMapper.mapCategoryEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Category>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsCategoryResponse>>> =
                remoteDataSource.getAllCategory()

            override suspend fun saveCallResult(data: List<ResultsCategoryResponse>) {
                val categoryList = DataMapper.mapCategoryResponseToEntities(data)
                localDataSource.insertCategory(categoryList)
            }
        }.asFlow()

    override fun getAllCashflow(month: String): Flow<Resource<List<Cashflow>>> =
        object : NetworkBoundResource<List<Cashflow>, List<ResultItemCashflowResponse>>() {
            override fun loadFromDB(): Flow<List<Cashflow>> {
                return localDataSource.getCashflowByMonth(month).map {
                    DataMapper.mapCashflowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Cashflow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultItemCashflowResponse>>> =
                remoteDataSource.getAllCashflow()

            override suspend fun saveCallResult(data: List<ResultItemCashflowResponse>) {
                val cashflowList = DataMapper.mapCashflowResponseToEntities(data)
                localDataSource.insertAllCashflow(cashflowList)
            }
        }.asFlow()

    override fun insertCashflow(cashflow: Cashflow) {
        val cashflowEntity = DataMapper.mapCashflowDomainToEntity(cashflow)
        appExecutors.diskIO().execute { localDataSource.insertCashflow(cashflowEntity) }
    }

    override fun setUpdateCashflow(cashflow: Cashflow) {
        val cashflowEntity = DataMapper.mapCashflowDomainToEntity(cashflow)
        appExecutors.diskIO().execute { localDataSource.setUpdateCashflow(cashflowEntity) }
    }

    override fun setDeleteCashflow(cashflow: Cashflow) {
        val cashflowEntity = DataMapper.mapCashflowDomainToEntity(cashflow)
        appExecutors.diskIO().execute { localDataSource.setDeleteCashflow(cashflowEntity) }
    }

    override fun getTotalNominal(jenis: String): Flow<Int> {
        return localDataSource.getTotalNominal(jenis)
    }

    override fun getReportData(jenis: String): Flow<List<Report>> {
        return localDataSource.getReportData(jenis).map {
            DataMapper.mapReportEntitiesToDomain(it)
        }
    }

}