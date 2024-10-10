package ru.autohub.feature.authentication

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

interface AuthBottomNavManager : LifecycleObserver {
    fun onAuthBottomNavStateChanged(

    )
}