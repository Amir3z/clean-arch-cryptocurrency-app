package com.amir.cryptocurrencies.presentation.coin_list

import com.amir.cryptocurrencies.domain.model.Coin

data class CoinListState(
    val coins: List<Coin> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
