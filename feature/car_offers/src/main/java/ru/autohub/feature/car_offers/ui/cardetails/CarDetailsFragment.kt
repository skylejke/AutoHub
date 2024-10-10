package ru.autohub.feature.car_offers.ui.cardetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.autohub.feature.car_offers.databinding.FragmentCarBinding
import ru.autohub.feature.common.utils.NavigableFragment

class CarDetailsFragment : NavigableFragment() {

    private var _binding: FragmentCarBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var _carPhotosAdapter: CarPhotosAdapter? = null
    private val carPhotosAdapter get() = requireNotNull(_carPhotosAdapter)

    private val carDetailsViewModel by viewModel<CarDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _carPhotosAdapter = CarPhotosAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCarBinding.inflate(layoutInflater).also {
        _binding = it
        setListeners()
    }.root

    private fun setListeners() {
        binding.fragmentCarToolbar.backIcon.setOnClickListener {
            navigator.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _carPhotosAdapter = null
    }
}
