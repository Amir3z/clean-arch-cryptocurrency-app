package com.amir.cryptocurrencies.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.amir.cryptocurrencies.presentation.UiEvent
import com.amir.cryptocurrencies.presentation.coin_list.CoinListEvent
import com.amir.cryptocurrencies.presentation.coin_list.CoinListViewModel

@Composable
fun CoinListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect() { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(CoinListEvent.TryToGetDataAgain)
                    }
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coin ->
                    CoinItem(
                        coin = coin,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(
                                CoinListEvent.OnItemClick(
                                    coin.id
                                )
                            )
                        })
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}
