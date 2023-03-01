package com.cookandroid.lottecinema_clone_project.main.response

import com.cookandroid.lottecinema_clone_project.main.data.Dates
import com.cookandroid.lottecinema_clone_project.main.data.Movie
import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : ArrayList<Movie>,
    @SerializedName("dates") val dates : Dates,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int
    )
