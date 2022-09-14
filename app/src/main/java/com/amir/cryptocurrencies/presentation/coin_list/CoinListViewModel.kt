package com.amir.cryptocurrencies.presentation.coin_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.cryptocurrencies.common.Constants.COIN_ID
import com.amir.cryptocurrencies.common.Resource
import com.amir.cryptocurrencies.domain.use_case.get_coins.GetCoinsUseCase
import com.amir.cryptocurrencies.presentation.Screen
import com.amir.cryptocurrencies.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> get() = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent get() = _uiEvent.receiveAsFlow()

    init {
        getCoins()
    }

    fun onEvent(event: CoinListEvent) {
        when (event) {
            is CoinListEvent.OnItemClick -> {
                sendUiEvent(
                    UiEvent.Navigate(
                        Screen.CoinDetailScreen.route + "/${event.id}"
                    )
                )
            }
            is CoinListEvent.TryToGetDataAgain -> {
                getCoins()
            }
        }
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        coins = resource.data ?: emptyList(),
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
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            message = resource.message ?: "Unknown Error",
                            action = "Try again"
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}