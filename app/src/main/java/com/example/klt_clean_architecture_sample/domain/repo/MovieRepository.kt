package com.example.klt_clean_architecture_sample.domain.repo

import com.example.klt_clean_architecture_sample.data.dto.detail.MovieDetailDto
import com.example.klt_clean_architecture_sample.data.dto.list.MovieDto
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovieList(
        pageNo: Int
    ): Response<List<MovieDto>>

    suspend fun getMovieDetail(
        id: Int,
        language: String
    ): Response<MovieDetailDto>
}