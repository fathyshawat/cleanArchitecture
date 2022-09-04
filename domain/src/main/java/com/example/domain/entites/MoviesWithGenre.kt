package com.example.domain.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesWithGenre(val id: Int?, val name: String?, val movies: List<MovieInfo>) : Parcelable