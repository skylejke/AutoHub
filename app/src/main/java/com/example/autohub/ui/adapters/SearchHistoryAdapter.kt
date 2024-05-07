package com.example.autohub.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autohub.R
import com.example.autohub.databinding.SearchHistoryListItemBinding
import com.example.autohub.domain.model.SearchHistoryVo

class SearchHistoryAdapter(private val clickable: Clickable) : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    var searchHistoryList = emptyList<SearchHistoryVo>()
        set(value) {
            val callback = object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return field.size
                }

                override fun getNewListSize(): Int {
                    return value.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return field[oldItemPosition] == value[newItemPosition]
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return field[oldItemPosition] == value[newItemPosition]
                }
            }
            val difResult = DiffUtil.calculateDiff(callback)
            difResult.dispatchUpdatesTo(this)
            field = value
        }

    interface Clickable {
        fun onItemClick(searchHistoryVo: SearchHistoryVo)
    }

    class SearchHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SearchHistoryListItemBinding.bind(view)

        fun bind(searchHistoryVo: SearchHistoryVo) = with(binding) {
            searchHistoryText.text = searchHistoryVo.query
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_history_list_item, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(searchHistoryList[position])
        holder.itemView.setOnClickListener {
            clickable.onItemClick(searchHistoryList[position])
        }
    }
}