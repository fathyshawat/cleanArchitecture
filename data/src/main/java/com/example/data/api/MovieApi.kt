package com.example.data.api

import com.example.data.modelResponse.GenreResponse
import com.example.data.modelResponse.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{type}")
    suspend fun getMovies(
        @Path("type") type: String,
        @Query("api_key") apiKey: String
    ): Response<MoviesResponse>


    @GET("genre/movie/list")
    suspend fun getGenre(@Query("api_key") apiKey: String): Response<GenreResponse>

}