package com.example.klt_clean_architecture_sample.domain.usecase.movie_detail

import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.detail.MovieDetailDto
import kotlinx.coroutines.flow.Flow

interface MovieDetailUseCase {
    operator fun invoke(id: Int, lang: String): Flow<Resource<MovieDetailDto>>
}