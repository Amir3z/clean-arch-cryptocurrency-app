package com.amir.cryptocurrencies.presentation

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ):UiEvent()
}
