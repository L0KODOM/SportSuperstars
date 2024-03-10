package com.lokodom.sportsuperstars.di

import android.content.Context
import androidx.room.Room
import com.lokodom.sportsuperstars.data.database.DbDataSource
import com.lokodom.sportsuperstars.data.database.dao.PlayerDao
import com.lokodom.sportsuperstars.data.network.SportsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideSportsApi(): SportsApi {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ): DbDataSource{
        return Room.databaseBuilder(context, DbDataSource::class.java, "player_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun playerDao(db: DbDataSource): PlayerDao = db.playerDao()

}