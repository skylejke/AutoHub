package ru.autohub.feature.common.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.autohub.navigation.Navigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


open class NavigableFragment : Fragment() {
    protected val navigator by inject<Navigator> { parametersOf(findNavController()) }
}