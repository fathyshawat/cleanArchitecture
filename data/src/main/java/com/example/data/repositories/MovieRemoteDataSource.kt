package com.example.data.repositories

import com.example.data.modelResponse.GenresItem
import com.example.domain.entites.MoviesWithGenre
import com.example.domain.common.Result
import kotlinx.coroutines.Deferred

interface MovieRemoteDataSource {
    suspend fun getGenreMovies(apiKey: String): List<GenresItem>
    suspend fun getRemoteMovies(
        type: String,
        apiKey: String,
        genre: List<GenresItem>
    ): Result<List<MoviesWithGenre>?>

}