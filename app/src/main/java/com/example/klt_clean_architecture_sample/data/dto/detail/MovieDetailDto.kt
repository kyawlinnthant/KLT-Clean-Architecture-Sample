package com.example.klt_clean_architecture_sample.data.dto.detail

import com.example.klt_clean_architecture_sample.common.Endpoint
import com.example.klt_clean_architecture_sample.data.dto.list.MovieDto
import com.example.klt_clean_architecture_sample.domain.model.DetailMovie
import com.example.klt_clean_architecture_sample.domain.model.ListMovie

data class MovieDetailDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)


fun MovieDetailDto.toViewData(): DetailMovie {
    return DetailMovie(
        id = id,
        image = Endpoint.IMAGE_URL + backdrop_path,
        title = original_title,
        rate = vote_average.toString(),
        rateCount = vote_count.toString(),
        releaseData = release_date,
        overview = overview
    )
}