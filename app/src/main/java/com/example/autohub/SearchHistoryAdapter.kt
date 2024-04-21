package com.example.autohub

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autohub.databinding.SearchHistoryListItemBinding
import com.example.autohub.model.SearchHistory

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    private var searchHistoryList = emptyList<SearchHistory>()

    class SearchHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SearchHistoryListItemBinding.bind(view)

        fun bind(searchHistory: SearchHistory) = with(binding) {
            searchHistoryText.text = searchHistory.query
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
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<SearchHistory>) {
        searchHistoryList = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        searchHistoryList = emptyList()
        notifyDataSetChanged()
    }
}