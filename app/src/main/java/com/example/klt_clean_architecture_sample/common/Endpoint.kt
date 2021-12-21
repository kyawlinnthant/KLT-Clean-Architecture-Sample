package com.example.klt_clean_architecture_sample.common

object Endpoint {

    const val BASE_URL =  "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
    private const val MOVIE = "movie/"

    const val GET_LIST = BASE_URL + MOVIE + "popular/"
    const val GET_DETAIL = "$BASE_URL$MOVIE{id}"
}