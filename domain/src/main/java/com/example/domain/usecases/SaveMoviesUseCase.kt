package com.example.domain.usecases

import com.example.domain.entites.MovieInfo
import com.example.domain.entites.MoviesWithGenre
import com.example.domain.repositories.MovieRepository
import javax.inject.Inject

class SaveMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieInfo: MovieInfo) = movieRepository.saveMovies(movieInfo)
}