package com.example.data.repositories

import com.example.data.database.MovieDao
import com.example.data.mapper.MoviesMapper
import com.example.domain.entites.MovieInfo
import com.example.domain.entites.MoviesWithGenre
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesLocalDataSourceImp @Inject constructor(
    private val movieDao: MovieDao,
    private val dispatcher: CoroutineDispatcher,
    private val mapper: MoviesMapper
) : MoviesLocalDataSource {

    override suspend fun getMovies(): Flow<List<MoviesWithGenre>> {
        val savedMoviesFlow = movieDao.getSavedMovies()
        return savedMoviesFlow.map { list ->
            list.map { element ->
                mapper.toMovie(element)
            }
        }
    }

    override suspend fun saveGenre(movie: MoviesWithGenre) = withContext(dispatcher) {
        movieDao.insertGenre(mapper.toGenreEntity(movie))
     }

    override suspend fun saveMovie(movie: MovieInfo) {
        movieDao.insertMovieItem(mapper.toEntityMovie(movie))
    }


    override suspend fun deleteAllMovies() = withContext(dispatcher) {
        movieDao.deleteAllMovies()
    }
}