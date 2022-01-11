package com.example.klt_clean_architecture_sample.domain.model

data class Movie(
    val id: Int,
    val image: String,
    val title: String,
    val rate: String,
    val rateCount: String,
    val releaseData: String,
    val overview: String
)
