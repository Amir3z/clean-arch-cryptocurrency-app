package com.amir.cryptocurrencies.data.remote

import com.amir.cryptocurrencies.data.remote.dto.CoinDetailsDto
import com.amir.cryptocurrencies.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {
    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(
        @Path("coinId") coinId: String
    ): CoinDetailsDto
}