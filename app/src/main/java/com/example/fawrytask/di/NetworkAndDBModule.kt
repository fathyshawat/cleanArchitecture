package com.example.fawrytask.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.data.api.MovieApi
import com.example.data.database.MovieDao
import com.example.data.database.MoviesDatabase
import com.example.fawrytask.BuildConfig
import com.example.fawrytask.common.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkAndDBModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)


    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, MoviesDatabase::class.java, Constants.DATABASE_NAME)
            .addMigrations()
            .build()

    @Singleton
    @Provides
    fun provideDao(db: MoviesDatabase): MovieDao =
        db.moviesDao()


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    fun provideSharedPref(app: Application): SharedPreferences =
        app.getSharedPreferences(Constants.SHARED_NAME, MODE_PRIVATE)


    @Provides
    fun provideEditShared(pref: SharedPreferences): SharedPreferences.Editor =
        pref.edit()

}