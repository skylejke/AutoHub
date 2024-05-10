package com.example.autohub.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.autohub.databinding.FragmentFavouriteBinding
import com.example.autohub.domain.model.CarVo
import com.example.autohub.ui.ScreenSwitchable
import com.example.autohub.ui.adapters.CarAdapter

class FavouriteFragment : Fragment(), ScreenSwitchable {

    private lateinit var binding: FragmentFavouriteBinding

    private lateinit var carAdapter: CarAdapter

    private val favouriteViewModel by viewModels<FavouriteViewModel> { FavouriteViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carAdapter = CarAdapter(object : CarAdapter.CarClickbale {
            override fun onCarClick(carVo: CarVo) {
                val args = FavouriteFragmentDirections.actionFavouriteFragmentToCarDetailsFragment(carVo)
                findNavController().navigate(args)
            }
        })

        binding.favouriteList.adapter = carAdapter

        favouriteViewModel.carsLiveData.observe(viewLifecycleOwner) {
            showProgressBar()
            try {
                carAdapter.carList = it
                if (carAdapter.carList.isEmpty()) {
                    showNoData()
                } else {
                    showData()
                    hideError()
                }
            } catch (e: Exception) {
                showError()
            } finally {
                hideProgressBar()
            }
        }
    }

    override fun showError() {
        binding.noConnectionPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun showNoData() {
        binding.emptyFavouritePlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideError() {
        binding.noConnectionPlaceHolder.root.visibility = View.GONE
    }

    override fun showData() {
        binding.emptyFavouritePlaceHolder.root.visibility = View.GONE
    }

    override fun showProgressBar() {
        binding.noConnectionPlaceHolder.root.visibility = View.GONE
        binding.progressBarPlaceHolder.root.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarPlaceHolder.root.visibility = View.GONE
    }
}