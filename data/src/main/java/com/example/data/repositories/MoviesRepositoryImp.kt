package com.example.data.repositories

import com.example.domain.common.Result
import com.example.domain.entites.MovieInfo
import com.example.domain.entites.MoviesWithGenre
import com.example.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {


    override suspend fun getRemoteMovies(
        type: String,
        apiKey: String
    ): Result<List<MoviesWithGenre>?> {
        val genreLS = remoteDataSource.getGenreMovies(apiKey)
        return remoteDataSource.getRemoteMovies(type, apiKey, genreLS)
    }

    override suspend fun getGenres(): Flow<List<MoviesWithGenre>> {
        return localDataSource.getMovies()
    }

    override suspend fun saveGenres(moviesWithGenre: MoviesWithGenre) {
        localDataSource.saveGenre(moviesWithGenre)
    }

    override suspend fun saveMovies(movieInfo: MovieInfo) {
        localDataSource.saveMovie(movieInfo)
    }


    override suspend fun deleteAllMovies() {
        localDataSource.deleteAllMovies()
    }
}