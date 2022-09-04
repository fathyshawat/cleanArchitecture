package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.Constants
import com.example.data.database.entity.GenreEntity
import com.example.data.database.entity.MovieItemEntity

@TypeConverters(Converters::class)
@Database(entities = arrayOf(GenreEntity::class,MovieItemEntity::class), version = Constants.DATABASE_VERSION)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao
}