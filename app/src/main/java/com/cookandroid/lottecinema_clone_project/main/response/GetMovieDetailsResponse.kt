package com.cookandroid.lottecinema_clone_project.main.response

import com.cookandroid.lottecinema_clone_project.main.data.Dates
import com.cookandroid.lottecinema_clone_project.main.data.Genre
import com.cookandroid.lottecinema_clone_project.main.data.Movie
import com.google.gson.annotations.SerializedName

data class GetMovieDetailsResponse(
    @SerializedName("title") val title : String,
    @SerializedName("genres") val genres : ArrayList<Genre>,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("vote_average") val vote_average : Float,
    @SerializedName("vote_count") val vote_count : Int,
    @SerializedName("runtime") val runtime : Int?,
    @SerializedName("poster_path") val poster_path : String?
)
