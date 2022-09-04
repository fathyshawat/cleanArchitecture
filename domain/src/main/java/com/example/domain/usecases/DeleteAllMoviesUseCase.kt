package com.example.domain.usecases

import com.example.domain.repositories.MovieRepository
import javax.inject.Inject

class DeleteAllMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() = movieRepository.deleteAllMovies()
}