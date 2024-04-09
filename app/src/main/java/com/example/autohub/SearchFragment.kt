package com.example.autohub

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation


class SearchFragment : Fragment() {

    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = view.findViewById<ImageButton>(R.id.back_icon)
        val cancelButton = view.findViewById<TextView>(R.id.cancel_button)
        searchEditText = view.findViewById(R.id.search_et)

        searchEditText.addTextChangedListener {  text: CharSequence? ->
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

    companion object {
        const val SEARCH_EDIT_TEXT = "SEARCH_EDIT_TEXT"
        const val SEARCH_EDIT_TEXT_DEFAULT = ""
    }
}
