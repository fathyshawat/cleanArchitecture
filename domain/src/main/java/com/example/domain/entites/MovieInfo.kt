package com.example.domain.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieInfo(
    val id: Int?,
    var genreId: Int?,
    val title: String?,
    val overview: String?,
    val backdropPath: String?,
    val genres: List<Int>?,
    val year: String?

) : Parcelable