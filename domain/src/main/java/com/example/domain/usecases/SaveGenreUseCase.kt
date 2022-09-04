package com.example.domain.usecases

import com.example.domain.entites.MoviesWithGenre
import com.example.domain.repositories.MovieRepository
import javax.inject.Inject

class SaveGenreUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(moviesWithGenre: MoviesWithGenre) = movieRepository.saveGenres(moviesWithGenre)
}