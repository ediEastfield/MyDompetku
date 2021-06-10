package com.dicoding.mydompetku.utils

import java.text.NumberFormat
import java.util.*

object FormatRupiah {
    fun formatRupiah(number: Int) : String {
        val localId = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localId)
        return format.format(number)
    }
}