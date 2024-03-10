package com.lokodom.sportsuperstars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lokodom.sportsuperstars.data.database.dao.PlayerDao
import com.lokodom.sportsuperstars.data.database.entities.FavPlayers

@Database(entities = [FavPlayers::class], version = 2)
abstract class DbDataSource: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}