package com.amir.cryptocurrencies.presentation.coin_list

sealed class CoinListEvent {
    data class OnItemClick(val id: String): CoinListEvent()
    object TryToGetDataAgain: CoinListEvent()
}
