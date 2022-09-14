package com.amir.cryptocurrencies.di

import com.amir.cryptocurrencies.common.Constants
import com.amir.cryptocurrencies.data.remote.CoinApi
import com.amir.cryptocurrencies.data.repository.CoinRepositoryImpl
import com.amir.cryptocurrencies.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: CoinApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

}