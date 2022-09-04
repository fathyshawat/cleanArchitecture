package com.example.data.database

import androidx.room.*
import com.example.data.database.entity.GenreEntity
import com.example.data.database.entity.MovieItemEntity
import com.example.data.database.entity.MovieWithGenreRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {



    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieItem(movies: MovieItemEntity)

    @Transaction
    @Query("SELECT * FROM GenreEntity")
    fun getSavedMovies(): Flow<List<MovieWithGenreRelation>>

    @Query("DELETE FROM GenreEntity")
    suspend fun deleteAllMovies()


}