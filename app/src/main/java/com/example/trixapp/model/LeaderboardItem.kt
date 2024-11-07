package com.example.trixapp.model

data class LeaderboardItem(
    val rank: Int,
    val name: String,
    val points: Int,
    val isIncreasing: Boolean
)