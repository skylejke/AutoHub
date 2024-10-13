package com.example.autohub.di

import androidx.navigation.NavController
import com.example.autohub.utils.NavigatorImpl
import ru.autohub.navigation.Navigator
import org.koin.dsl.module

val navigationModule = module {
    factory<Navigator> {
        (navController: NavController) -> NavigatorImpl(navController)
    }
}
