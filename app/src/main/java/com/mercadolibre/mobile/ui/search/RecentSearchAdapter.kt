package com.mercadolibre.mobile.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.domain.entities.RecentSearch
import com.mercadolibre.mobile.databinding.ItemSearchBinding

class RecentSearchAdapter(private val listener: RecentSearchListener?) :
    RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {

    private var values: List<RecentSearch> = listOf()

    interface RecentSearchListener {
        fun onClickRecentSearch(item: RecentSearch)
    }

    fun setItems(items: List<RecentSearch>) {
        values = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textView.text = values[position].query
        holder.itemView.setOnClickListener {
            listener?.onClickRecentSearch(item)
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView: TextView = binding.textViewQuery
    }

}
