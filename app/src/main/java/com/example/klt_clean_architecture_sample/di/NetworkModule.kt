package com.example.klt_clean_architecture_sample.di

import com.example.klt_clean_architecture_sample.BuildConfig
import com.example.klt_clean_architecture_sample.common.Endpoint
import com.example.klt_clean_architecture_sample.data.ws.ApiService
import com.example.klt_clean_architecture_sample.data.ws.WsDataSource
import com.example.klt_clean_architecture_sample.data.ws.WsDataSourceImpl
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideWsDataSource(apiService: ApiService): WsDataSource {
        return WsDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Endpoint.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder().addInterceptor(OkHttpProfilerInterceptor())
                .addNetworkInterceptor(OkHttpProfilerInterceptor())
                .build()
        } else {
            OkHttpClient.Builder().build()
        }
    }
}