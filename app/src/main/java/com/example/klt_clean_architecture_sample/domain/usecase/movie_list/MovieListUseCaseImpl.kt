package com.example.klt_clean_architecture_sample.domain.usecase.movie_list

import com.example.klt_clean_architecture_sample.common.BaseDataSource
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.list.MovieDto
import com.example.klt_clean_architecture_sample.di.QualifierModule
import com.example.klt_clean_architecture_sample.domain.repo.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieListUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    @QualifierModule.Io private val io: CoroutineDispatcher
) : BaseDataSource(),MovieListUseCase {

    override fun invoke(page : Int): Flow<Resource<List<MovieDto>>> {
        return flow {
            emit(
                safeApiCall {
                    movieRepository.getMovieList(page)
                }
            )
        }.flowOn(io)
    }
}