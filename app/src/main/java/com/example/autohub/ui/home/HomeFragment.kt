package com.example.autohub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.autohub.R
import com.example.autohub.databinding.FragmentHomeBinding
import com.example.autohub.domain.model.CarVo
import com.example.autohub.ui.MainActivity
import com.example.autohub.ui.ScreenSwitchable
import com.example.autohub.ui.adapters.CarAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(), ScreenSwitchable {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var carAdapter: CarAdapter

    private val homeViewModel by viewModel<HomeViewModel> { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).showBottomNavigation()
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sortAdapter = ArrayAdapter(
            requireContext(),
            R.layout.sort_spinner_item,
            resources.getStringArray(R.array.sort_array)
        )

        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sortSpinner.adapter = sortAdapter

        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    val selectedSortFilter =
                        when (parent.getItemAtPosition(position).toString()) {
                            "mileage min" -> "mileage:asc"
                            "price min" -> "price:asc"
                            "price max" -> "price:desc"
                            else -> "-"
                        }
                    showNoData()
                    if (selectedSortFilter == "-") {
                        lifecycleScope.launch {
                            homeViewModel.get()
                        }
                    } else {
                        lifecycleScope.launch {
                            homeViewModel.sort(selectedSortFilter)
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                lifecycleScope.launch {
                    homeViewModel.get()
                }
            }
        }


        carAdapter = CarAdapter(object : CarAdapter.CarClickbale {
            override fun onCarClick(carVo: CarVo) {
                val args = HomeFragmentDirections.actionHomeFragmentToCarDetailsFragment(carVo)
                findNavController().navigate(args)
            }
        })

        binding.carList.adapter = carAdapter

        homeViewModel.carsLiveData.observe(viewLifecycleOwner) { records ->
            carAdapter.carList = records.list
            binding.carCounter.text = if (records.list.isEmpty()) {
                "0 offers"
            } else {
                hideProgressBar()
                "${records.list.size} offers"
            }
        }

        lifecycleScope.launch {
            homeViewModel.get()
        }

        binding.noConnectionPlaceHolder.retryButton.setOnClickListener {
            lifecycleScope.launch {
                homeViewModel.get()
            }
        }

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

    override fun showError() {
        binding.noConnectionPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun showNoData() {
        binding.carList.visibility = View.GONE
    }

    override fun hideError() {
        binding.noConnectionPlaceHolder.root.visibility = View.GONE
    }

    override fun showData() {
        binding.carList.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        binding.noConnectionPlaceHolder.root.visibility = View.GONE
        binding.progressBarPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.GONE
    }
}