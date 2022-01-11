package com.example.klt_clean_architecture_sample.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.list.toVO
import com.example.klt_clean_architecture_sample.domain.model.Movies
import com.example.klt_clean_architecture_sample.domain.usecase.movie_list.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val listUseCase: MovieListUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<Resource<List<Movies>>>(Resource.loading())
    val movies get() = _movies.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {

        listUseCase(1).onEach { result ->

            when (result.status) {

                Resource.Status.SUCCESS -> {

                    val movies = mutableListOf<Movies>()
                    result.data?.forEach {
                        it.results.forEach { item ->
                            movies.add(item.toVO())
                        }
                    }
                    
                    _movies.value = Resource.success(movies)
                }
                Resource.Status.ERROR -> {
                    _movies.value = Resource.error(result.message ?: "Some Error!!")
                }
                Resource.Status.LOADING -> {
                    _movies.value = Resource.loading(null)
                }
            }
        }.launchIn(viewModelScope)
    }
}