package com.amir.cryptocurrencies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amir.cryptocurrencies.common.Constants
import com.amir.cryptocurrencies.common.Constants.COIN_ID
import com.amir.cryptocurrencies.presentation.coin_detail.components.CoinDetailScreen
import com.amir.cryptocurrencies.presentation.coin_list.components.CoinListScreen
import com.amir.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrenciesTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.CoinListScreen.route
                ) {

                    composable(Screen.CoinListScreen.route) {
                        CoinListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(
                        route = Screen.CoinDetailScreen.route + "/{$COIN_ID}",
                        arguments = listOf(
                            navArgument(COIN_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        CoinDetailScreen()
                    }

                }

            }
        }
    }
}