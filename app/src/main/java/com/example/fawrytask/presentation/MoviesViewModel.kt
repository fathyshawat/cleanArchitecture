package com.example.fawrytask.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entites.MoviesWithGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.domain.common.Result
import com.example.domain.usecases.*
import com.example.fawrytask.common.Constants
import com.example.fawrytask.common.Utils
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getRemoteMoviesUseCase: GetRemoteMoviesUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val saveGenreUseCase: SaveGenreUseCase,
    private val saveMovieUseCase: SaveMoviesUseCase,
    private val deleteAllMoviesUseCase: DeleteAllMoviesUseCase,
    private val sharedPreferences: SharedPreferences,
    private val edit: SharedPreferences.Editor
) : ViewModel() {


    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _movies = MutableLiveData<List<MoviesWithGenre>>()
    val moviesLv = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun getMovies() {
        viewModelScope.launch {
            _dataLoading.postValue(true)

            val currentTime = Utils.getTimeNow()
            val savedTime = sharedPreferences.getLong(Constants.TIME_CASH, 0)
            if (currentTime < savedTime) {
                getDataLocal()
            } else {
                getRemoteData()

            }

        }

    }



    private suspend fun getRemoteData() {
        when (val mv =
            getRemoteMoviesUseCase.invoke(Constants.TYPE, Constants.API_KEY)) {
            is Result.Success -> {
                _dataLoading.postValue(false)
                val movies: List<MoviesWithGenre> = mv.data!!
                editSharedPre()
                deleteAllMoviesUseCase.invoke()
                for (genre in movies) {
                    saveGenreUseCase.invoke(genre)
                    for (movie in genre.movies) {
                        movie.genreId = genre.id
                        saveMovieUseCase.invoke(movie)
                    }
                }
                moviesLv.value = movies


            }
            is Result.Error -> {
                _dataLoading.postValue(false)
                _error.postValue(mv.exception.message)
            }
        }
    }

    private fun getDataLocal() {
        viewModelScope.launch {
            val movie = getLocalMoviesUseCase.invoke()
            movie.collect { mv ->
                moviesLv.value = mv
                _dataLoading.postValue(false)
            }
        }


    }

    private fun editSharedPre() {
        edit.putLong(Constants.TIME_CASH, Utils.getTimeAfterFourHours())
        edit.apply()
    }

}