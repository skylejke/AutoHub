package com.example.autohub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.autohub.R
import com.example.autohub.databinding.FragmentCarBinding


class CarFragment : Fragment() {

    private lateinit var binding: FragmentCarBinding

    private val args: CarFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.carInfo.text = getString(R.string.car_info, args.car.make, args.car.model, args.car.year)
        binding.year.text = args.car.year.toString()
        binding.body.text = args.car.bodyType
        binding.mileage.text = args.car.mileage
        binding.price.text = args.car.price
        binding.color.text = args.car.displayColor
        Glide.with(requireContext())
            .load(args.car.primaryPhotoUrl)
            .into(binding.carPhoto)

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}