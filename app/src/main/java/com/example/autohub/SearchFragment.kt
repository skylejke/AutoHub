package com.example.autohub

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
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.autohub.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var searchEditText: EditText

    private lateinit var carAdapter: CarAdapter

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

        carAdapter = CarAdapter()
        binding.searchCarList.adapter = carAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                cancelButton.visibility = if (text?.isNotEmpty() == true) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }

            override fun afterTextChanged(text: Editable?) {
                binding.searchButton.setOnClickListener {
                    lifecycleScope.launch(Dispatchers.IO) {
                        try {
                            val records =
                                RetrofitCarInstance.api.searchCarsByMake(text.toString())
                            withContext(Dispatchers.Main) {
                                carAdapter.setList(records.list)
                            }
                        } catch (e: Exception) {
                            Log.e("Error", e.stackTraceToString())
                        }
                    }
                }
            }
        })

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
