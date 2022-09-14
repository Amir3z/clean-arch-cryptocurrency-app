package com.amir.cryptocurrencies.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.cryptocurrencies.common.Constants
import com.amir.cryptocurrencies.common.Resource
import com.amir.cryptocurrencies.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> get() = _state

    init {
        savedStateHandle.get<String>(Constants.COIN_ID)?.let { id ->
            getCoin(id)
        }
    }

    private fun getCoin(id: String) {
        getCoinUseCase(id).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        coinDetail = resource.data,
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        error = resource.message ?: "Unknown Error",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}