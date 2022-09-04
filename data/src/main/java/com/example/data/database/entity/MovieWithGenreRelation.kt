package com.example.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation


data class MovieWithGenreRelation(
    @Embedded
    val genre: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "gId"
    )
    val movie: List<MovieItemEntity>
)