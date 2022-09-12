package com.amir.cryptocurrencies.domain.model

import com.amir.cryptocurrencies.data.remote.dto.TeamMember

data class CoinDetail(
    val id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val team: List<TeamMember>,
    val tags: List<String>
)
