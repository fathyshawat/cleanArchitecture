package com.example.domain.repositories

import com.example.domain.common.Result
import com.example.domain.entites.MovieInfo
import com.example.domain.entites.MoviesWithGenre
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getRemoteMovies(type: String, apiKey: String): Result<List<MoviesWithGenre>?>

    suspend fun getGenres(): Flow<List<MoviesWithGenre>>

    suspend fun saveGenres(moviesWithGenre: MoviesWithGenre)

    suspend fun saveMovies(movieInfo: MovieInfo)

    suspend fun deleteAllMovies()
}