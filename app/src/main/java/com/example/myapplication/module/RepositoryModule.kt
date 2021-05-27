package com.example.myapplication.module


import com.example.myapplication.repository.MovieRepository
import com.example.myapplication.repository.impl.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoviesRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
