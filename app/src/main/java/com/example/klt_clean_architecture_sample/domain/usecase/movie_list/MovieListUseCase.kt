package com.example.klt_clean_architecture_sample.domain.usecase.movie_list

import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.list.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieListUseCase {
    operator fun invoke(page : Int) : Flow<Resource<List<MovieDto>>>
}