package com.example.klt_clean_architecture_sample.data.dto.list

import com.example.klt_clean_architecture_sample.common.Endpoint
import com.example.klt_clean_architecture_sample.domain.model.ListMovie

data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDto.toViewData() : ListMovie{
    return ListMovie(
        id = id,
        image = Endpoint.IMAGE_URL + backdrop_path,
        title = original_title,
        rate = vote_average.toString()
    )
}
