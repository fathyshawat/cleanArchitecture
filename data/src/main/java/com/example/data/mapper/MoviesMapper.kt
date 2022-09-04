package com.example.data.mapper

import android.util.Log
import com.example.data.database.entity.GenreEntity
import com.example.data.database.entity.MovieItemEntity
import com.example.data.database.entity.MovieWithGenreRelation
import com.example.data.modelResponse.GenreResponse
import com.example.data.modelResponse.GenresItem
import com.example.data.modelResponse.MoviesResponse
import com.example.domain.entites.MovieInfo
import com.example.domain.entites.MoviesWithGenre
import com.google.gson.Gson

class MoviesMapper {

    fun toGenreListMapper(genreResponse: GenreResponse): List<GenresItem> {
        return genreResponse.genres
    }

    fun toMovieList(movieResponse: MoviesResponse): List<MovieInfo> {
        val results = movieResponse.results
        return results.map {
            MovieInfo(it.id, 0, it.title, it.overview, it.backdropPath, it.genreIds,it.releaseDate)
        }
    }


    fun toMovieListMapper(
        moviesList: List<MovieInfo>,
        genres: List<GenresItem>
    ): List<MoviesWithGenre> {
        val res: ArrayList<MoviesWithGenre> = ArrayList()

        genres.forEach { genre ->
            val movies = moviesList.filter { movie -> movie.genres?.contains(genre.id)!! }
            if (movies.isNotEmpty()) {

                for (movie in movies) {
                    movie.genreId = genre.id
                }
                res.add(
                    MoviesWithGenre(
                        genre.id,
                        genre.name,
                        movies
                    )
                )
            }
        }

        return res
    }


    fun toMovie(genreEntity: MovieWithGenreRelation): MoviesWithGenre {
        return MoviesWithGenre(
            genreEntity.genre.genreId, genreEntity.genre.name, genreEntity.movie.map {
                MovieInfo(it.movieId, it.gId, it.title, it.overview, it.imageUrl, it.genres,it.year)
            }

        )
    }

    fun toGenreEntity(movie: MoviesWithGenre): GenreEntity {
        return GenreEntity(movie.id!!, movie.name)
    }

    fun toEntityMovie(movie: MovieInfo): MovieItemEntity {
        return MovieItemEntity(
            movie.id.toString().plus(movie.genreId),
            movieId = movie.id!!,
            gId = movie.genreId!!,
            title = movie.title,
            overview = movie.overview,
            imageUrl = movie.backdropPath,
            genres = movie.genres,
            year = movie.year
        )
    }


}
