package com.amir.cryptocurrencies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amir.cryptocurrencies.common.Constants
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
                        route = Screen.CoinDetailScreen.route + "?${Constants.COIN_ID}=${Constants.COIN_ID}",
                        arguments = listOf(
                            navArgument(name = Constants.COIN_ID) {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ) {

                    }

                }

            }
        }
    }
}