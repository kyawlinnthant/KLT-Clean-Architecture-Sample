package com.example.klt_clean_architecture_sample.data.ws

import com.example.klt_clean_architecture_sample.data.dto.detail.MovieDetailDto
import com.example.klt_clean_architecture_sample.data.dto.list.MovieDto
import retrofit2.Response
import javax.inject.Inject

class WsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : WsDataSource {
    override suspend fun getMovieList(
        key: String,
        pageNo: Int
    ): Response<List<MovieDto>> {
        return apiService.fetchMovieList(
            key,
            pageNo
        )
    }

    override suspend fun getMovieDetail(
        key: String,
        id: Int,
        language: String
    ): Response<MovieDetailDto> {
        return apiService.fetchMovieDetail(
            id,
            key,
            language
        )
    }
}