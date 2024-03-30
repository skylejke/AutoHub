package com.example.autohub

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation


class SearchFragment : Fragment() {

    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = view.findViewById<ImageButton>(R.id.back_icon)
        val cancelButton = view.findViewById<TextView>(R.id.cancel_button)
        searchEditText = view.findViewById(R.id.search_et)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cancelButton.visibility = if (s?.isNotEmpty() == true) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        cancelButton.setOnClickListener {
            searchEditText.text.clear()
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
