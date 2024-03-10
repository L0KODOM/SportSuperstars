package com.lokodom.sportsuperstars.di

import com.lokodom.sportsuperstars.data.repository.MainRepository
import com.lokodom.sportsuperstars.data.repository.MainRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: MainRepositoryImp): MainRepository

}