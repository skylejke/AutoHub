package ru.autohub.feature.search.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.autohub.feature.common.utils.NavigableFragment
import ru.autohub.feature.search.databinding.FragmentSearchBinding

class SearchFragment : NavigableFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _searchHistoryAdapter: SearchHistoryAdapter? = null
    private val searchHistoryAdapter get() = requireNotNull(_searchHistoryAdapter)

    private lateinit var searchEditText: EditText

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _searchHistoryAdapter = SearchHistoryAdapter {
            navigator.fromSearchFragmentToSearchResultsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater).also {
            _binding = it
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.searchHistory.collect { history ->
                Log.d("ddd", "frag $history")
                searchHistoryAdapter.submitList(history)
            }
        }

        lifecycleScope.launch {
            viewModel.loadSearchHistory()
        }

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
                    lifecycleScope.launch {
                        viewModel.updateSearchHistory(query)
                    }
                    navigator.fromSearchFragmentToSearchResultsFragment()
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
            lifecycleScope.launch {
                viewModel.clearSearchHistory()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _searchHistoryAdapter = null
    }
}
