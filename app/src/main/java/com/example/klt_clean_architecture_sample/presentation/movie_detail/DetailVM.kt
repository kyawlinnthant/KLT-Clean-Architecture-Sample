package com.example.klt_clean_architecture_sample.presentation.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.detail.toViewData
import com.example.klt_clean_architecture_sample.domain.model.DetailMovie
import com.example.klt_clean_architecture_sample.domain.usecase.movie_detail.MovieDetailUseCase
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DetailVM @Inject constructor(
    private val detailUseCase: MovieDetailUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<Resource<DetailMovie>>()
    val movie: LiveData<Resource<DetailMovie>> get() = _movie

    private fun getMovie(id: Int) {
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
        }
    }
}

