package com.lokodom.sportsuperstars.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lokodom.sportsuperstars.data.database.entities.FavPlayers
import com.lokodom.sportsuperstars.domain.model.Player

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player:FavPlayers)

    @Query("SELECT * FROM favPlayers ORDER BY id DESC")
    fun getAll(): LiveData<List<FavPlayers>>

    @Delete
    fun delete(player: FavPlayers)

}