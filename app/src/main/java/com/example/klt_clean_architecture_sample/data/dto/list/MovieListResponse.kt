package com.example.klt_clean_architecture_sample.data.dto.list

data class MovieListResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)