package com.example.klt_clean_architecture_sample.domain.usecase.movie_detail

import com.example.klt_clean_architecture_sample.common.BaseDataSource
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.detail.MovieDetailDto
import com.example.klt_clean_architecture_sample.di.QualifierModule
import com.example.klt_clean_architecture_sample.domain.repo.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @QualifierModule.Io private val io: CoroutineDispatcher
) : BaseDataSource() {
    operator fun invoke(id: Int, lang: String): Flow<Resource<MovieDetailDto>> {
        return flow {
            emit(
                safeApiCall {
                    movieRepository.getMovieDetail(id, lang)
                }
            )
        }.flowOn(io)
    }
}