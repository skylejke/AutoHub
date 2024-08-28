package com.example.autohub.ui.cardetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.autohub.R
import com.example.autohub.databinding.FragmentCarBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCarBinding

    private val args: CarDetailsFragmentArgs by navArgs()

    private val carDetailsViewModel by viewModel<CarDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.carCharacteristicsContainer) {
            year.text = args.car.year.toString()
            body.text = args.car.bodyType
            mileage.text = args.car.mileage
            color.text = args.car.displayColor
            vin.text = args.car.vin
        }

        with(binding.carTitleContainer) {
            carInfo.text =
                getString(R.string.car_info, args.car.make, args.car.model, args.car.year)
            price.text = args.car.price
            carPhoto.load(args.car.primaryPhotoUrl)
        }

        val carMap = hashMapOf(
            "bodyType" to args.car.bodyType,
            "condition" to args.car.condition,
            "displayColor" to args.car.displayColor,
            "id" to args.car.id,
            "make" to args.car.make,
            "mileage" to args.car.mileage,
            "model" to args.car.model,
            "photoUrls" to args.car.photoUrls,
            "price" to args.car.price,
            "primaryPhotoUrl" to args.car.primaryPhotoUrl,
            "vin" to args.car.vin,
            "year" to args.car.year
        )

        var isFavorite = false


        carDetailsViewModel.checkIfCarIsFavoutrite(args.car)
            .observe(viewLifecycleOwner) { isInFavouriteList ->
                isFavorite = isInFavouriteList ?: false
                binding.fragmentCarToolbar.favIcon.setBackgroundResource(if (isFavorite) R.drawable.favorite else R.drawable.favorite_border)
            }

        binding.fragmentCarToolbar.favIcon.setOnClickListener {
            carDetailsViewModel.getCurrentUser().observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        carDetailsViewModel.addToFavourite(args.car.id.toString(), carMap)
                        binding.fragmentCarToolbar.favIcon.setBackgroundResource(R.drawable.favorite)
                        Toast.makeText(
                            requireContext(),
                            "Added car to favourite",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        carDetailsViewModel.deleteFromFavourite(args.car.id.toString())
                        binding.fragmentCarToolbar.favIcon.setBackgroundResource(R.drawable.favorite_border)
                        Toast.makeText(
                            requireContext(),
                            "Removed car from favourite",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Log in to add cars to your favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.fragmentCarToolbar.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
