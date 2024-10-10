package ru.autohub.navigation

interface Navigator{
    fun popBackStack()
    fun fromSettingsFragmentToAboutFragment()
    fun fromSettingsFragmentToSignInFragment()
    fun fromSearchResultsFragmentToCarDetailsFragment()
    fun fromSignInFragmentToHomeFragment()
    fun fromSignInFragmentToSignUpFragment()
    fun fromSignUpFragmentToSignInFragment()
    fun fromSignUpFragmentToHomeFragment()
    fun fromHomeFragmentToSettingsFragment()
    fun fromHomeFragmentToCarDetailsFragment()
    fun fromFavouriteFragmentToCarDetailsFragment()
    fun fromSearchFragmentToSearchResultsFragment()
}