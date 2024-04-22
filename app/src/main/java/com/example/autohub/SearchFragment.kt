package com.example.autohub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.autohub.MainActivity.Companion.SEARCH_HISTORY_KEY
import com.example.autohub.MainActivity.Companion.SEARCH_HISTORY_PREFERENCES
import com.example.autohub.databinding.FragmentSearchBinding
import com.example.autohub.model.SearchHistory


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var searchEditText: EditText

    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    private lateinit var searchHistoryList: MutableList<SearchHistory>

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        searchHistoryAdapter = SearchHistoryAdapter()
        binding.searchHistoryList.adapter = searchHistoryAdapter

        sharedPrefs =
            requireContext().getSharedPreferences(SEARCH_HISTORY_PREFERENCES, Context.MODE_PRIVATE)

        val searchHistoryPrefs = sharedPrefs.getString(SEARCH_HISTORY_KEY, null)?.split(",")

        if (!searchHistoryPrefs.isNullOrEmpty()) {
            searchHistoryList = searchHistoryPrefs.map { SearchHistory(it) }.toMutableList()
            searchHistoryAdapter.setList(searchHistoryList)
        } else {
            searchHistoryList = mutableListOf()
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

                    bundle.putString("QUERY", text.toString())
                    findNavController().navigate(
                        R.id.action_searchFragment_to_searchResultsFragment,
                        bundle
                    )

                    if (text.toString().isNotEmpty()) {
                        val query = text.toString()
                        searchHistoryList.add(0, SearchHistory(query))
                        if (searchHistoryList.size > 10) {
                            searchHistoryList.removeAt(searchHistoryList.lastIndex)
                        }

                        sharedPrefs.edit().putString(
                            SEARCH_HISTORY_KEY,
                            searchHistoryList.joinToString(separator = ",") { it.query }
                        ).apply()
                    }
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
            sharedPrefs.edit().clear().apply()
            searchHistoryAdapter.clearList()
        }

        binding.backIcon.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_EDIT_TEXT, searchEditText.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchEditText.setText(
            savedInstanceState?.getString(
                SEARCH_EDIT_TEXT,
                SEARCH_EDIT_TEXT_DEFAULT
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SEARCH_EDIT_TEXT = "SEARCH_EDIT_TEXT"
        const val SEARCH_EDIT_TEXT_DEFAULT = ""
    }
}
