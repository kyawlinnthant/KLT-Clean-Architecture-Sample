package com.example.klt_clean_architecture_sample.presentation.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.detail.toViewData
import com.example.klt_clean_architecture_sample.domain.model.Movie
import com.example.klt_clean_architecture_sample.domain.usecase.movie_detail.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val detailUseCase: MovieDetailUseCase
) : ViewModel() {

    private val _movie = MutableStateFlow<Resource<Movie>>(Resource.loading())
    val movie get() = _movie.asStateFlow()


    fun getMovie(id: Int) {
        detailUseCase(id, "en").onEach { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    val movie = result.data!!.toViewData()
                    _movie.value = Resource.success(movie)
                }
                Resource.Status.ERROR -> {
                    _movie.value = Resource.error(result.message ?: "Some Error!!")
                }
                Resource.Status.LOADING -> {
                    _movie.value = Resource.loading(null)
                }
            }
        }.launchIn(viewModelScope)
    }
}

