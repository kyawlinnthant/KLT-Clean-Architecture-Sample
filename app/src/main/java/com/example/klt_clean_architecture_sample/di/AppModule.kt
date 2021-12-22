package com.example.klt_clean_architecture_sample.di

import com.example.klt_clean_architecture_sample.data.ws.WsDataSource
import com.example.klt_clean_architecture_sample.domain.repo.MovieRepository
import com.example.klt_clean_architecture_sample.domain.repo.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(wsDataSource: WsDataSource): MovieRepository {
        return MovieRepositoryImpl(wsDataSource)
    }



}