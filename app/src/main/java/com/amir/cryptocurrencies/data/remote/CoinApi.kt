package com.amir.cryptocurrencies.data.remote

import com.amir.cryptocurrencies.data.remote.dto.CoinDetailsDto
import com.amir.cryptocurrencies.data.remote.dto.CoinDto
import com.amir.cryptocurrencies.data.remote.dto.PriceDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(
        @Path("coinId") coinId: String
    ): CoinDetailsDto

    @GET("/v1/coins/{coinId}/ohlcv/latest")
    suspend fun getPriceInfo(
        @Path("coinId") coinId: String
    ): List<PriceDto>
}