package com.example.klt_clean_architecture_sample.data.ws

import com.example.klt_clean_architecture_sample.data.dto.detail.MovieDetailDto
import com.example.klt_clean_architecture_sample.data.dto.list.ListDTO
import retrofit2.Response

interface WsDataSource {

    suspend fun getMovieList(
        key: String,
        pageNo: Int
    ): Response<List<ListDTO>>

    suspend fun getMovieDetail(
        key: String,
        id: Int,
        language: String
    ): Response<MovieDetailDto>

}