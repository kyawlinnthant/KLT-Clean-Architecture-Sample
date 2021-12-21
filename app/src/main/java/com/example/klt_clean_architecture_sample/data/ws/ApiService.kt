package com.example.klt_clean_architecture_sample.data.ws

import com.example.klt_clean_architecture_sample.data.dto.detail.MovieDetailDto
import com.example.klt_clean_architecture_sample.data.dto.list.MovieDto
import com.example.klt_clean_architecture_sample.common.Endpoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Endpoint.GET_LIST)
    suspend fun fetchMovieList(
        @Query("api_key")key : String,
        @Query("page")pageNumber : Int
    ) : Response<List<MovieDto>>

    @GET(Endpoint.GET_DETAIL)
    suspend fun fetchMovieDetail(
        @Path("id")movieId : Int,
        @Query("api_key")key : String,
        @Query("language")language : String
    ) : Response<MovieDetailDto>

}