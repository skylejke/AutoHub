package ru.autohub.feature.settings.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.autohub.feature.common.utils.NavigableFragment
import ru.autohub.feature.settings.databinding.FragmentAboutBinding


class AboutFragment : NavigableFragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAboutBinding.inflate(layoutInflater).also {
        _binding = it
        setListeners()
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.fragmentAboutToolbar.backIcon.setOnClickListener {
            navigator.popBackStack()
        }
    }
}