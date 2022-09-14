package com.amir.cryptocurrencies.data.repository

import com.amir.cryptocurrencies.common.Resource
import com.amir.cryptocurrencies.data.remote.CoinApi
import com.amir.cryptocurrencies.domain.model.Coin
import com.amir.cryptocurrencies.domain.model.CoinDetail
import com.amir.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
    ) : CoinRepository {

    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        try {

            val result = api.getCoins().map { it.toCoin() }
            emit(Resource.Success(result))

        } catch (ex: HttpException) {

            emit(Resource.Error(message = ex.localizedMessage ?: "Unknown Error"))

        } catch (ex: IOException) {

            emit(Resource.Error(message = ex.message ?: "Check your internet!"))

        }
    }

    override fun getCoinById(id: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())

        try {
            val coinDetail = api.getCoin(id).toCoinDetail()
            val coinPrice = api.getPriceInfo(id).map { it.toPrice() }.first()
            emit(Resource.Success(coinDetail.copy(price = coinPrice)))

        } catch (ex: HttpException) {

            emit(Resource.Error(message = ex.message() ?: "Unknown Error"))

        } catch (ex: IOException) {

            emit(Resource.Error(message = ex.message ?: "Check your internet!"))

        }
    }
}