package com.example.klt_clean_architecture_sample.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klt_clean_architecture_sample.common.Resource
import com.example.klt_clean_architecture_sample.data.dto.list.toViewData
import com.example.klt_clean_architecture_sample.domain.model.ListMovie
import com.example.klt_clean_architecture_sample.domain.usecase.movie_list.MovieListUseCase
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ListVM @Inject constructor(
    private val listUseCase: MovieListUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<Resource<List<ListMovie>>>()
    val movies : LiveData<Resource<List<ListMovie>>> get() = _movies

    private fun getMovies() {
        listUseCase(1).onEach { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    val movies = result.data!!.map { it.toViewData() }
                    _movies.value = Resource.success(movies)
                }
                Resource.Status.ERROR -> {
                    _movies.value = Resource.error(result.message?:"Some Error!!")
                }
                Resource.Status.LOADING -> {
                    _movies.value = Resource.loading(null)
                }
            }
        }
    }
}