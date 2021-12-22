package com.example.klt_clean_architecture_sample.presentation.movie_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.list.toViewData
import com.example.klt_clean_architecture_sample.domain.model.ListMovie
import com.example.klt_clean_architecture_sample.domain.usecase.movie_list.MovieListUseCase
import com.example.klt_clean_architecture_sample.domain.usecase.movie_list.MovieListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val listUseCase: MovieListUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<Resource<List<ListMovie>>>(Resource.loading())
    val movies get() = _movies.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        Log.d("reach1","here")

        listUseCase(1).onEach { result ->
            Log.d("reach2","here")
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    val movies = result.data!!.map { it.toViewData() }
                    _movies.value = Resource.success(movies)
                }
                Resource.Status.ERROR -> {
                    _movies.value = Resource.error(result.message ?: "Some Error!!")
                }
                Resource.Status.LOADING -> {
                    _movies.value = Resource.loading(null)
                }
            }
        }
    }
}