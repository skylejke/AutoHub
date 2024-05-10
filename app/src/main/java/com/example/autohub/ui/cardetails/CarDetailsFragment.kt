package com.example.autohub.ui.cardetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.autohub.R
import com.example.autohub.databinding.FragmentCarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class CarDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCarBinding

    private val args: CarDetailsFragmentArgs by navArgs()

    private val dataBase = FirebaseFirestore.getInstance()

    private val carDetailsViewModel by viewModels<CarDetailsViewModel> { CarDetailsViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            carInfo.text =
                getString(R.string.car_info, args.car.make, args.car.model, args.car.year)
            year.text = args.car.year.toString()
            price.text = args.car.price
            body.text = args.car.bodyType
            mileage.text = args.car.mileage
            color.text = args.car.displayColor
            vin.text = args.car.vin
            carPhoto.load(args.car.primaryPhotoUrl)
        }

        val carMap = hashMapOf(
            "bodyType" to args.car.bodyType,
            "condition" to args.car.condition,
            "displayColor" to args.car.displayColor,
            "id" to args.car.id,
            "make" to args.car.make,
            "mileage" to args.car.mileage,
            "mileageUnformatted" to args.car.mileageUnformatted,
            "model" to args.car.model,
            "photoUrls" to args.car.photoUrls,
            "price" to args.car.price,
            "priceUnformatted" to args.car.priceUnformatted,
            "primaryPhotoUrl" to args.car.primaryPhotoUrl,
            "vin" to args.car.vin,
            "year" to args.car.year
        )

        var isFavorite = false

        carDetailsViewModel.checkIfCarIsFavoutrite(args.car).observe(viewLifecycleOwner) { isInFavouriteList ->
            isFavorite = isInFavouriteList
            Log.e("POPA", isInFavouriteList.toString())
            if (isFavorite) {
                binding.favIcon.setBackgroundResource(R.drawable.favorite)
            } else {
                binding.favIcon.setBackgroundResource(R.drawable.favorite_border)
            }
        }

//        dataBase.collection("users")
//            .document(FirebaseAuth.getInstance().currentUser!!.uid)
//            .collection("favourite")
//            .addSnapshotListener { value, error ->
//                if (error != null) {
//                    return@addSnapshotListener
//                }
//                val favouriteList = value!!.documents.map { it.toObject(CarVo::class.java)!! }
//                if (favouriteList.contains(args.car)) {
//                    binding.favIcon.setBackgroundResource(R.drawable.favorite)
//                    isFavorite = true
//                } else {
//                    binding.favIcon.setBackgroundResource(R.drawable.favorite_border)
//                    isFavorite = false
//                }
//            }

        binding.favIcon.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                dataBase.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection("favourite").document(args.car.id.toString()).set(carMap)
                binding.favIcon.setBackgroundResource(R.drawable.favorite)
                Toast.makeText(requireContext(), "Added car to favourite", Toast.LENGTH_SHORT)
                    .show()
            } else {
                dataBase.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection("favourite").document(args.car.id.toString()).delete()
                binding.favIcon.setBackgroundResource(R.drawable.favorite_border)
                Toast.makeText(requireContext(), "Removed car from favourite", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}