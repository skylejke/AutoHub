package com.example.autohub

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.autohub.databinding.FragmentSearchResultsBinding
import com.example.autohub.model.Records
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class SearchResultsFragment : Fragment(), ScreenSwitchable {


    private var _binding: FragmentSearchResultsBinding? = null

    private val binding get() = _binding!!

    private lateinit var carAdapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carAdapter = CarAdapter()
        binding.searchResultsList.adapter = carAdapter

        val query = arguments?.getString("QUERY").toString()


        if (query.isEmpty() || query !in resources.getStringArray(R.array.makes)) {
            carAdapter.clearList()
            showNoData()
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                makeRequest(query)
            }
        }

        binding.noConnectionPlaceHolder.retryButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                makeRequest(query)
            }
        }

        binding.backIcon.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }


    private suspend fun makeRequest(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<Records> = RetrofitCarInstance.api.searchCarsByMake(query)
                if (response.isSuccessful) {
                    val cars: Records? = response.body()
                    val carsList = cars?.list ?: emptyList()
                    if (carsList.isEmpty()) {
                        withContext(Dispatchers.Main) {
                            hideError()
                            showNoData()
                        }
                        return@launch
                    } else {
                        withContext(Dispatchers.Main) {
                            hideError()
                            showData()
                            carAdapter.setList(carsList)
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Exception on", e.stackTraceToString())
                    showError()
                }
            }
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
}