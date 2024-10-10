package ru.autohub.feature.common.utils

fun interface OnAdapterItemClick<T> {
    fun onClick(t: T)
}