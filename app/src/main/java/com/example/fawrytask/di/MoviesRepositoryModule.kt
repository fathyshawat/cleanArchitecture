package com.example.fawrytask.di

import com.example.data.api.MovieApi
import com.example.data.database.MovieDao
import com.example.data.mapper.MoviesMapper

import com.example.data.repositories.*
import com.example.domain.repositories.MovieRepository
import com.example.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher


@Module(includes = [NetworkAndDBModule::class])
@InstallIn(SingletonComponent::class)
object MoviesRepositoryModule {

    @Provides
    fun provideResponseMapper(): MoviesMapper = MoviesMapper()



    @Provides
    fun provideMovieRemoteDataSourceImp(
        service: MovieApi,
        mapper: MoviesMapper
    ): MovieRemoteDataSource {
        return MovieRemoteDataSourceImp(service, mapper)
    }

    @Provides
    fun provideMoviesLocalDataSourceImp(
        dao: MovieDao,
        dispatcher: CoroutineDispatcher,
        mapper: MoviesMapper
    ): MoviesLocalDataSource {
        return MoviesLocalDataSourceImp(dao, dispatcher, mapper)
    }

    @Provides
    fun provideMoviesRepositoryImp(
        localDataSource: MoviesLocalDataSource,
        remoteDataSource: MovieRemoteDataSource
    ): MoviesRepositoryImp =
        MoviesRepositoryImp(localDataSource, remoteDataSource)

    @Provides
    fun provideMovieRepository(
        localDataSource: MoviesLocalDataSource,
        remoteDataSource: MovieRemoteDataSource
    ): MovieRepository =
        MoviesRepositoryImp(localDataSource, remoteDataSource)

    @Provides
    fun provideRemoteUseCase(movieRepository: MovieRepository): GetRemoteMoviesUseCase =
        GetRemoteMoviesUseCase(movieRepository)

    @Provides
    fun provideDeleteAllMoviesUseCase(movieRepository: MovieRepository): DeleteAllMoviesUseCase =
        DeleteAllMoviesUseCase(movieRepository)

    @Provides
    fun provideGetLocalMoviesUseCase(movieRepository: MovieRepository): GetLocalMoviesUseCase =
        GetLocalMoviesUseCase(movieRepository)

    @Provides
    fun provideSaveGenreUseCase(movieRepository: MovieRepository): SaveGenreUseCase =
        SaveGenreUseCase(movieRepository)

    @Provides
    fun provideSaveMovieUseCase(movieRepository: MovieRepository): SaveMoviesUseCase =
        SaveMoviesUseCase(movieRepository)

}