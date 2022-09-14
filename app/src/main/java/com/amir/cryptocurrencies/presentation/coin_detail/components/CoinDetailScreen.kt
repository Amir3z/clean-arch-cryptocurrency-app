package com.amir.cryptocurrencies.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amir.cryptocurrencies.presentation.coin_detail.CoinDetailViewModel
import com.amir.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val coinDetail = state.coinDetail

    Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            coinDetail?.let {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})",
                                style = MaterialTheme.typography.h2,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .weight(8f)
                            )
                            Text(
                                text = if (coinDetail.isActive) "active" else "inactive",
                                color = if (coinDetail.isActive) Color.Green else Color.Red,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .weight(2f)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "$ ${coinDetail.price.toString().take(8)}",
                            style = MaterialTheme.typography.h3,
                            color = Color.Green
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = coinDetail.description,
                            style = MaterialTheme.typography.body2
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.h3
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (tag in coinDetail.tags) {
                                CoinTag(tag = tag)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        if(coinDetail.team.isNotEmpty()){
                            Text(
                                text = "Team members",
                                style = MaterialTheme.typography.h3
                            )
                        Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    items(coinDetail.team) { teamMember ->
                        TeamMemberItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Divider()
                    }

                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

        }
    }

}
