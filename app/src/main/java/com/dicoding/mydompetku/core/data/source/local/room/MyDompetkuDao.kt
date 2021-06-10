package com.dicoding.mydompetku.core.data.source.local.room

import androidx.room.*
import com.dicoding.mydompetku.core.data.source.local.entity.CashflowEntity
import com.dicoding.mydompetku.core.data.source.local.entity.CategoryEntity
import com.dicoding.mydompetku.core.data.source.local.entity.ReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDompetkuDao {

    @Query("SELECT * FROM category WHERE jenis = :jenis")
    fun getAllCategory(jenis: String): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE jenis = 'debt' OR jenis = 'loan'")
    fun getDebtLoanCategory(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: List<CategoryEntity>)

    @Query("SELECT * FROM cashflow WHERE strftime('%m', date) = :month ORDER BY date DESC")
    fun getCashflowByMonth(month: String): Flow<List<CashflowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCashflow(cashflow: List<CashflowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCashflow(cashflow: CashflowEntity)

    @Update
    fun updateCashflow(cashflow: CashflowEntity)

    @Delete
    fun deleteCashflow(cashflow: CashflowEntity)

    @Query("SELECT SUM(nominal) FROM cashflow WHERE jenis = :jenis")
    fun getTotalNominal(jenis: String): Flow<Int>

    @Query("SELECT category, SUM(nominal) as total FROM cashflow WHERE jenis = :jenis GROUP BY category")
    fun getReportData(jenis: String): Flow<List<ReportEntity>>
}