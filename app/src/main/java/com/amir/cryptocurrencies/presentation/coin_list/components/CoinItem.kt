package com.amir.cryptocurrencies.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.amir.cryptocurrencies.domain.model.Coin
import com.amir.cryptocurrencies.presentation.coin_list.CoinListEvent

@Composable
fun CoinItem(
    coin: Coin,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(CenterVertically)
        )
        Text(
            text = if (coin.isActive) "active" else "inactive",
            color = if (coin.isActive) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.End,
            modifier = Modifier.align(CenterVertically)
        )

    }

}