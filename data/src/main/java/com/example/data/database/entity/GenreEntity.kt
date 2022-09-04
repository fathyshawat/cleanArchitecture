package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey
    val genreId: Int,
    val name: String?,

    )