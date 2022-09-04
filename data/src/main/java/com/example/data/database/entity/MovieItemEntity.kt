package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class MovieItemEntity(
    @PrimaryKey
    val id: String,
    val movieId: Int,
    val gId: Int,
    val title: String?,
    val overview: String?,
    val imageUrl: String?,
    val year: String?,
    val genres: List<Int>?,
)