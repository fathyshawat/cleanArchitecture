package com.example.data.repositories

import com.example.data.database.entity.GenreEntity
import com.example.data.database.entity.MovieItemEntity
import com.example.domain.entites.MovieInfo
import com.example.domain.entites.MoviesWithGenre
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun getMovies(): Flow<List<MoviesWithGenre>>

    suspend fun saveGenre(movie: MoviesWithGenre)

    suspend fun saveMovie(movie: MovieInfo)

    suspend fun deleteAllMovies()
}