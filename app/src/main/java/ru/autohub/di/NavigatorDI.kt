package ru.autohub.di

import androidx.navigation.NavController
import org.koin.dsl.module
import ru.autohub.navigation.Navigator
import ru.autohub.utils.NavigatorImpl

val navigationModule = module {
    factory<Navigator> {
        (navController: NavController) -> NavigatorImpl(navController)
    }
}
