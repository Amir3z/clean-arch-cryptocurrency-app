package com.amir.cryptocurrencies.data.remote.dto

import com.amir.cryptocurrencies.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName

data class CoinDetailsDto(
    val description: String,
    val development_status: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: String,
    val links: Links,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>,
    val message: String,
    val name: String,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: String,
    @SerializedName("proof_type")
    val proofType: String,
    val rank: Int,
    @SerializedName("started_at")
    val startedAt: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val type: String,
    val whitePaper: WhitePaper
) {
    fun toCoinDetail(): CoinDetail {
        return CoinDetail(
            id,
            name,
            description,
            symbol,
            rank,
            team,
            tags.map { it.name },
            isActive
        )
    }
}