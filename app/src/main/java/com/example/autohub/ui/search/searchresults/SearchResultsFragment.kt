package com.example.autohub.ui.search.searchresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.autohub.R
import com.example.autohub.databinding.FragmentSearchResultsBinding
import com.example.autohub.ui.ScreenSwitchable
import com.example.autohub.ui.adapters.CarAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchResultsFragment : Fragment(), ScreenSwitchable {

    private lateinit var binding: FragmentSearchResultsBinding

    private lateinit var carAdapter: CarAdapter

    private val args: SearchResultsFragmentArgs by navArgs()

    private val searchResultsViewModel by viewModel<SearchResultsViewModel> { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carAdapter = CarAdapter { item ->
            val args =
                SearchResultsFragmentDirections.actionSearchResultsFragmentToCarDetailsFragment(item)
            findNavController().navigate(args)
        }

        binding.searchResultsList.adapter = carAdapter

        searchResultsViewModel.carsLiveData.observe(viewLifecycleOwner) { records ->
            carAdapter.carList = records.list
            if (records.list.isNotEmpty()) {
                hideProgressBar()
            }
        }

        val query = args.query

        if (query.isEmpty() || query !in resources.getStringArray(R.array.makes)) {
            showNoData()
            carAdapter.carList = emptyList()
        } else {
            lifecycleScope.launch {
                searchResultsViewModel.get(query)
            }
        }

        binding.noConnectionPlaceHolder.retryButton.setOnClickListener {
            lifecycleScope.launch {
                searchResultsViewModel.get(query)
            }
        }

        binding.backIcon.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }

    override fun showError() {
        binding.noConnectionPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun showNoData() {
        binding.noDataPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideError() {
        binding.noConnectionPlaceHolder.root.visibility = View.GONE
    }

    override fun showData() {
        binding.noDataPlaceHolder.root.visibility = View.GONE
    }

    override fun showProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.GONE
    }
}