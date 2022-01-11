package com.example.klt_clean_architecture_sample.domain.repo

import com.example.klt_clean_architecture_sample.BuildConfig.API_KEY
import com.example.klt_clean_architecture_sample.data.dto.detail.MovieDetailDto
import com.example.klt_clean_architecture_sample.data.dto.list.ListDTO
import com.example.klt_clean_architecture_sample.data.ws.WsDataSource
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val wsDataSource: WsDataSource
) : MovieRepository {
    override suspend fun getMovieList(pageNo: Int): Response<List<ListDTO>> {
        return wsDataSource.getMovieList(API_KEY, pageNo)
    }

    override suspend fun getMovieDetail(id: Int, language: String): Response<MovieDetailDto> {
        return wsDataSource.getMovieDetail(API_KEY, id, language)
    }
}