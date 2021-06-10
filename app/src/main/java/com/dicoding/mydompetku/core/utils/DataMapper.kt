package com.dicoding.mydompetku.core.utils

import com.dicoding.mydompetku.core.data.source.local.entity.CashflowEntity
import com.dicoding.mydompetku.core.data.source.local.entity.CategoryEntity
import com.dicoding.mydompetku.core.data.source.local.entity.ReportEntity
import com.dicoding.mydompetku.core.data.source.remote.response.ResultItemCashflowResponse
import com.dicoding.mydompetku.core.data.source.remote.response.ResultsCategoryResponse
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.model.Category
import com.dicoding.mydompetku.core.domain.model.Report

object DataMapper {

    fun mapCategoryEntitiesToDomain(input: List<CategoryEntity>) : List<Category> =
        input.map {
            Category(
                categoryId = it.categoryId,
                title = it.title,
                icon = it.icon,
                jenis = it.jenis
            )
        }

    fun mapCashflowEntitiesToDomain(input: List<CashflowEntity>) : List<Cashflow> =
        input.map {
            Cashflow(
                cashflowId = it.cashflowId,
                icon = it.icon,
                category = it.category,
                jenis = it.jenis,
                note = it.notes,
                date = it.date,
                nominal = it.nominal
            )
        }

    fun mapReportEntitiesToDomain(input: List<ReportEntity>) : List<Report> =
        input.map {
            Report(
                category = it.category,
                total = it.total
            )
        }

    fun mapCategoryResponseToEntities(input: List<ResultsCategoryResponse>) : List<CategoryEntity> {
        val categoryList = ArrayList<CategoryEntity>()
        input.map {
            val category = CategoryEntity(
                categoryId = it.id,
                title = it.title,
                icon = it.icon,
                jenis = it.jenis
            )
            categoryList.add(category)
        }
        return categoryList
    }

    fun mapCashflowResponseToEntities(input: List<ResultItemCashflowResponse>) : List<CashflowEntity> {
        val cashflowList = ArrayList<CashflowEntity>()
        input.map {
            val cashflow = CashflowEntity(
                cashflowId = it.id,
                icon = it.icon,
                category = it.category,
                jenis = it.jenis,
                notes = it.note,
                date = it.dates,
                nominal = it.nominal
            )
            cashflowList.add(cashflow)
        }
        return cashflowList
    }

    fun mapCashflowDomainToEntity(input: Cashflow) = CashflowEntity(
        cashflowId = input.cashflowId,
        icon = input.icon,
        category = input.category,
        jenis = input.jenis,
        notes = input.note,
        date = input.date,
        nominal = input.nominal
    )
}