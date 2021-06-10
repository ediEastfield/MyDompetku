package com.dicoding.mydompetku.core.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.databinding.ItemListCashflowBinding
import com.dicoding.mydompetku.utils.FormatRupiah
import kotlin.collections.ArrayList


class CashflowAdapter : RecyclerView.Adapter<CashflowAdapter.ListViewHolder>() {

    private var listData = ArrayList<Cashflow>()
    var onItemClick: ((Cashflow) -> Unit)? = null

    fun setData(newListData: List<Cashflow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int) =
        ListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_cashflow, viewGroup, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(
        listViewHolder: ListViewHolder,
        position: Int
    ) {
        val data = listData[position]
        listViewHolder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCashflowBinding.bind(itemView)
        fun bind(data: Cashflow){
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.icon)
                    .into(ivIconCashflow)

                tvDateCashflow.text = data.date
                tvCategoryCashflow.text = data.category
                tvNoteCashflow.text = data.note
                tvNominalCashflow.text = FormatRupiah.formatRupiah(data.nominal)
                if (data.jenis  == "income") {
                    tvNominalCashflow.setTextColor(Color.parseColor("#2AA80A"))
                }
                if (data.jenis == "expense"){
                    tvNominalCashflow.setTextColor(Color.parseColor("#F41212"))
                } else {
                    tvNominalCashflow.setTextColor(Color.parseColor("#000000"))
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}