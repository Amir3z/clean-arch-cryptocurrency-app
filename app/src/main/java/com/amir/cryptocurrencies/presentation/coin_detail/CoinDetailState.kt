package com.amir.cryptocurrencies.presentation.coin_detail

import com.amir.cryptocurrencies.domain.model.Coin
import com.amir.cryptocurrencies.domain.model.CoinDetail

data class CoinDetailState(
    val coinDetail: CoinDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
