package com.example.klt_clean_architecture_sample.domain.model

data class DetailMovie(
    val id: Int,
    val image: String,
    val title: String,
    val rate: String,
    val rateCount: String,
    val releaseData: String,
    val overview: String
)
