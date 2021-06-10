package com.dicoding.mydompetku.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.core.domain.model.Category
import com.dicoding.mydompetku.databinding.ItemListCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {

    private var listData = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null

    fun setData(newListData: List<Category>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int) =
        ListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_category, viewGroup, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(
        listViewHolder: ListViewHolder,
        position: Int
    ) {
        val data = listData[position]
        listViewHolder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCategoryBinding.bind(itemView)
        fun bind(data: Category) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.icon)
                    .into(ivCategoryIcon)

                tvCategoryName.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}