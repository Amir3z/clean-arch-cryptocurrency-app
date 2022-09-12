package com.amir.cryptocurrencies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.amir.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrenciesTheme {

            }
        }
    }
}