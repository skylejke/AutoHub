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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

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

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val records =
                    RetrofitCarInstance.api.getCars("ZrQEPSkKZGFuaWxtZWdhMjAwM0BnbWFpbC5jb20=")
                withContext(Dispatchers.Main) {
                    carAdapter.setList(records.list)
                    binding.carCounter.text = records.list.size.toString() + " объявлений"
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
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

}