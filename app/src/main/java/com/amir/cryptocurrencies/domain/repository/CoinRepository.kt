package com.amir.cryptocurrencies.domain.repository

import com.amir.cryptocurrencies.common.Resource
import com.amir.cryptocurrencies.domain.model.Coin
import com.amir.cryptocurrencies.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<Resource<List<Coin>>>

    fun getCoinById(id: String): Flow<Resource<CoinDetail>>

}