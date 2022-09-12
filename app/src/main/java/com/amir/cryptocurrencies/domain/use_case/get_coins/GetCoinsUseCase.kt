package com.amir.cryptocurrencies.domain.use_case.get_coins

import com.amir.cryptocurrencies.common.Resource
import com.amir.cryptocurrencies.domain.model.Coin
import com.amir.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }
}