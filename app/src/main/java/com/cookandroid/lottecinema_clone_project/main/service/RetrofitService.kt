package com.cookandroid.lottecinema_clone_project.main.service

import com.cookandroid.lottecinema_clone_project.main.response.GetMovieDetailsResponse
import com.cookandroid.lottecinema_clone_project.main.response.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String = "<<api_key>>",
        @Query("language") language: String = "ko",
        @Query("page") page: Int,
        @Query("region") region: String = "KR"
    ): Call<GetMoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String = "<<api_key>>",
        @Query("language") language: String = "ko",
        @Query("page") page: Int,
        @Query("region") region: String = "KR"
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("api_key") api_key: String = "<<api_key>>",
        @Query("language") language: String = "ko",
        @Query("page") page: Int,
        @Query("region") region: String = "KR"
    ): Call<GetMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") api_key: String = "<<api_key>>",
        @Query("language") language: String = "ko"
    ): Call<GetMovieDetailsResponse>
}