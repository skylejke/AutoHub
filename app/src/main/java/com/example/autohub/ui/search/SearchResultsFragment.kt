package com.example.autohub.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.autohub.R
import com.example.autohub.databinding.FragmentSearchResultsBinding
import com.example.autohub.domain.model.CarDomain
import com.example.autohub.ui.ScreenSwitchable
import com.example.autohub.ui.adapters.CarAdapter
import kotlinx.coroutines.launch

class SearchResultsFragment : Fragment(), ScreenSwitchable {

    private lateinit var binding: FragmentSearchResultsBinding

    private lateinit var carAdapter: CarAdapter

    private val searchResultsViewModel by viewModels<SearchResultsViewModel> {
        SearchResultsViewModelFactory(
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carAdapter = CarAdapter()
        binding.searchResultsList.adapter = carAdapter

        searchResultsViewModel.carsLiveData.observe(viewLifecycleOwner) { records ->
            carAdapter.carList = records.list
            if (records.list.isNotEmpty()) {
                hideProgressBar()
            }
        }

        val query = arguments?.getString("QUERY").toString()

        if (query.isEmpty() || query !in resources.getStringArray(R.array.makes)) {
            showNoData()
            carAdapter.carList = emptyList()
        } else {
            lifecycleScope.launch {
                searchResultsViewModel.get(query)
            }
        }

        carAdapter.carClickbale = object : CarAdapter.CarClickbale {
            override fun onCarClick(carDomain: CarDomain) {
                val args = SearchResultsFragmentDirections.actionSearchResultsFragmentToCarFragment(carDomain)
                findNavController().navigate(args)
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