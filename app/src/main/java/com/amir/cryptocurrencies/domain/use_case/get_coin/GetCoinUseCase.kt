package com.amir.cryptocurrencies.domain.use_case.get_coin

import com.amir.cryptocurrencies.common.Resource
import com.amir.cryptocurrencies.domain.model.Coin
import com.amir.cryptocurrencies.domain.model.CoinDetail
import com.amir.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<Resource<CoinDetail>> {
        return repository.getCoinById(id)
    }
}