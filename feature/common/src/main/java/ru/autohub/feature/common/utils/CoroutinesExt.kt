package ru.autohub.feature.common.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> withMainContext(
    block: suspend CoroutineScope.() -> T
): T = withContext(Dispatchers.Main, block)

