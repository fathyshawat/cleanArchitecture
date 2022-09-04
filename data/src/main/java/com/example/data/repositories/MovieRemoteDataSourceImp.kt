package com.example.data.repositories

import com.example.data.api.MovieApi
import com.example.data.mapper.MoviesMapper
import com.example.data.modelResponse.GenresItem
import com.example.domain.entites.MoviesWithGenre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.domain.common.Result


class MovieRemoteDataSourceImp @Inject constructor(
    private val service: MovieApi,
    private val mapper: MoviesMapper
) : MovieRemoteDataSource {

    override suspend fun getRemoteMovies(
        type: String,
        apiKey: String,
        genre: List<GenresItem>
    ): Result<List<MoviesWithGenre>?> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getMovies(type, apiKey)
                if (response.isSuccessful) {
                    return@withContext Result.Success(
                        mapper.toMovieListMapper(
                            mapper.toMovieList(response.body()!!), genre)
                    )
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getGenreMovies(apiKey: String): List<GenresItem> =
        withContext(Dispatchers.IO) {
            val response = service.getGenre(apiKey)

            try {
                if (response.isSuccessful) {
                    return@withContext mapper.toGenreListMapper(response.body()!!)
                } else {
                    return@withContext emptyList<GenresItem>()
                }
            } catch (e: Exception) {
                return@withContext emptyList()
            }
        }
}