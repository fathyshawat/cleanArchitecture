package com.example.domain.usecases

import com.example.domain.repositories.MovieRepository
import javax.inject.Inject

class GetRemoteMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(type: String, apiKey: String) =
        movieRepository.getRemoteMovies(type, apiKey)
}