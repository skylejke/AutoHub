package ru.autohub.feature.search.ui.searchresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.autohub.feature.common.adapters.CarAdapter
import ru.autohub.feature.common.utils.NavigableFragment
import ru.autohub.feature.common.utils.ScreenSwitchable
import ru.autohub.feature.search.databinding.FragmentSearchResultsBinding

class SearchResultsFragment : NavigableFragment(), ScreenSwitchable {

    private var _binding: FragmentSearchResultsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _carAdapter: CarAdapter? = null
    private val carAdapter get() = requireNotNull(_carAdapter)

    private val searchResultsViewModel by viewModel<SearchResultsViewModel> { parametersOf(this) }

    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _carAdapter = CarAdapter {
            navigator.fromSearchResultsFragmentToCarDetailsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchResultsBinding.inflate(layoutInflater).also {
        _binding = it
        setViews()
        setListeners()
    }.root

    private fun setViews() {

        binding.searchResultsList.adapter = carAdapter

        lifecycleScope.launch {
            searchResultsViewModel.searchResultsStateFlow.collect { records ->
                carAdapter.submitList(records.list)
                if (records.list.isNotEmpty()) {
                    hideProgressBar()
                }
            }
        }
    }

    private fun setListeners() {
        binding.noConnectionPlaceHolder.retryButton.setOnClickListener {
            lifecycleScope.launch {
                searchResultsViewModel.get(query)
            }
        }

        binding.fragmentSearchResultsToolbar.backIcon.setOnClickListener {
            navigator.popBackStack()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _carAdapter = null
    }
}