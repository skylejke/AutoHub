package com.example.autohub.ui.search.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autohub.databinding.FragmentSearchBinding
import com.example.autohub.domain.model.SearchHistoryVo
import com.example.autohub.ui.adapters.SearchHistoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchEditText: EditText
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchHistoryAdapter = SearchHistoryAdapter(object : SearchHistoryAdapter.Clickable {
            override fun onItemClick(searchHistoryVo: SearchHistoryVo) {
                val args = SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(
                    searchHistoryVo.query
                )
                findNavController().navigate(args)
            }
        })

        binding.searchHistoryList.adapter = searchHistoryAdapter

        viewModel.searchHistory.observe(viewLifecycleOwner) { history ->
            searchHistoryAdapter.searchHistoryList = history
        }

        viewModel.loadSearchHistory()

        searchEditText = binding.searchEt

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                binding.cancelButton.visibility = if (text?.isNotEmpty() == true) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }

            override fun afterTextChanged(text: Editable?) {
                binding.searchButton.setOnClickListener {
                    val query = text.toString()

                    viewModel.updateSearchHistory(query)

                    val args =
                        SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(query)
                    findNavController().navigate(args)
                }
            }
        })

        binding.cancelButton.setOnClickListener {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

            imm?.let { inputManager ->
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
                searchEditText.text.clear()
            }
        }

        binding.clearSearchHistoryButton.setOnClickListener {
            viewModel.clearSearchHistory()
        }
    }
}
