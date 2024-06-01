package com.example.autohub.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autohub.databinding.FragmentFavouriteBinding
import com.example.autohub.ui.adapters.CarAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

    private lateinit var carAdapter: CarAdapter

    private val favouriteViewModel by viewModel<FavouriteViewModel>()

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

        carAdapter = CarAdapter { item ->
            val args =
                FavouriteFragmentDirections.actionFavouriteFragmentToCarDetailsFragment(item)
            findNavController().navigate(args)
        }

        binding.favouriteList.adapter = carAdapter

        favouriteViewModel.getCurrentUser().observe(viewLifecycleOwner) { user ->
            if (user != null) {
                favouriteViewModel.carsLiveData.observe(viewLifecycleOwner) { favouriteList ->
                    carAdapter.carList = favouriteList
                    if (carAdapter.carList.isEmpty()) {
                        binding.emptyFavouritePlaceHolder.root.visibility = View.VISIBLE
                    } else {
                        binding.emptyFavouritePlaceHolder.root.visibility = View.GONE
                    }
                }
            } else {
                binding.notAuthorizedUserPlaceHolder.root.visibility = View.VISIBLE
            }
        }
    }
}