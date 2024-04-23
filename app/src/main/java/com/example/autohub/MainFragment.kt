package com.example.autohub

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.autohub.databinding.FragmentMainBinding
import com.example.autohub.model.Records
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainFragment : Fragment(), ScreenSwitchable {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    private lateinit var carAdapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        carAdapter = CarAdapter()
        binding.carList.adapter = carAdapter

        showProgressBar()
        lifecycleScope.launch(Dispatchers.IO) {
            makeRequest()
        }

        binding.noConnectionPlaceHolder.retryButton.setOnClickListener {
            showProgressBar()
            lifecycleScope.launch(Dispatchers.IO) {
                makeRequest()
            }
        }

        binding.searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private suspend fun makeRequest() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<Records> = RetrofitCarInstance.api.getCars()
                if (response.isSuccessful) {
                    val cars: Records? = response.body()
                    val carsList = cars?.list ?: emptyList()
                    if (carsList.isEmpty()) {
                        withContext(Dispatchers.Main) {
                            hideError()
                            showNoData()
                            binding.carCounter.text = carsList.size.toString() + " объявлений"
                        }
                        return@launch
                    } else {
                        withContext(Dispatchers.Main) {
                            hideError()
                            showData()
                            carAdapter.setList(carsList)
                            binding.carCounter.text = carsList.size.toString() + " объявлений"
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Exception on", e.stackTraceToString())
                    showError()
                }
            } finally {
                withContext(Dispatchers.Main) {
                    hideProgressBar()
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

    override fun showProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.GONE
    }

}