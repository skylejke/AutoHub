package com.example.autohub

import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.autohub.feature.authentication.ui.signin.SignInFragment
import ru.autohub.feature.common.utils.NavigableFragment
import ru.autohub.navigation.Navigator

class NavigatorImpl(private val navController: NavController) : Navigator {

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun fromSettingsFragmentToAboutFragment() {
        navController.navigate(R.id.action_settingsFragment_to_aboutFragment)
    }

    override fun fromSettingsFragmentToSignInFragment() {
        navController.navigate(R.id.action_settingsFragment_to_signInFragment)
    }

    override fun fromSearchResultsFragmentToCarDetailsFragment() {
        navController.navigate(R.id.action_searchResultsFragment_to_carDetailsFragment)
    }

    override fun fromSearchFragmentToSearchResultsFragment() {
        navController.navigate(R.id.action_searchFragment_to_searchResultsFragment)
    }

    override fun fromSignInFragmentToHomeFragment() {
        navController.navigate(R.id.action_signInFragment_to_homeFragment)
    }

    override fun fromSignInFragmentToSignUpFragment() {
        navController.navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    override fun fromSignUpFragmentToSignInFragment() {
        navController.navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    override fun fromSignUpFragmentToHomeFragment() {
        navController.navigate(R.id.action_signUpFragment_to_homeFragment)
    }

    override fun fromHomeFragmentToSettingsFragment() {
        navController.navigate(R.id.action_homeFragment_to_settingsFragment)
    }

    override fun fromHomeFragmentToCarDetailsFragment() {
        navController.navigate(R.id.action_homeFragment_to_carDetailsFragment)
    }

    override fun fromFavouriteFragmentToCarDetailsFragment() {
        navController.navigate(R.id.action_favouriteFragment_to_carDetailsFragment)
    }
}