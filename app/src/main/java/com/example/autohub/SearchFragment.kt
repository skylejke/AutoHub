package com.example.autohub

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.autohub.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var searchEditText: EditText

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

        val backButton = binding.backIcon
        val cancelButton = binding.cancelButton
        searchEditText = binding.searchEt

        searchEditText.addTextChangedListener { text: CharSequence? ->
            cancelButton.visibility = if (!text.isNullOrEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        cancelButton.setOnClickListener {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

            imm?.let { inputManager ->
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
                searchEditText.text.clear()
            }
        }

        backButton.setOnClickListener {
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
