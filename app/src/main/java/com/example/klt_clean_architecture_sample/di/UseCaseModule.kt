package com.example.klt_clean_architecture_sample.di

import com.example.klt_clean_architecture_sample.domain.repo.MovieRepository
import com.example.klt_clean_architecture_sample.domain.usecase.movie_detail.MovieDetailUseCase
import com.example.klt_clean_architecture_sample.domain.usecase.movie_detail.MovieDetailUseCaseImpl
import com.example.klt_clean_architecture_sample.domain.usecase.movie_list.MovieListUseCase
import com.example.klt_clean_architecture_sample.domain.usecase.movie_list.MovieListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMovieListUseCase(
        repo: MovieRepository,
        @QualifierModule.Io io: CoroutineDispatcher
    ): MovieListUseCase {
        return MovieListUseCaseImpl(repo, io)
    }

    @Provides
    @Singleton
    fun provideMovieDetailUseCase(
        repo: MovieRepository,
        @QualifierModule.Io io: CoroutineDispatcher
    ): MovieDetailUseCase {
        return MovieDetailUseCaseImpl(repo, io)
    }
}