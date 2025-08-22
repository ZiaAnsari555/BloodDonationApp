package com.yodeck.models.movies

data class MoviesResponse(
    val page: Int,
    val results: ArrayList<Result>,
    val total_pages: Int,
    val total_results: Int
)