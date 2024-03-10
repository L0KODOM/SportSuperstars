package com.lokodom.sportsuperstars.data.repository

import androidx.lifecycle.LiveData
import com.lokodom.sportsuperstars.data.database.entities.FavPlayers
import com.lokodom.sportsuperstars.domain.model.Player
import com.lokodom.sportsuperstars.domain.model.TablePosition
import com.lokodom.sportsuperstars.domain.model.Team

interface MainRepository {

    suspend fun getSearchedPlayer(name:String): Player

    suspend fun getLeagueTeams(sport: String, country: String): List<Team>

    suspend fun getTableResults(id: String, season: String): List<TablePosition>

    suspend fun insertInPlayerDb(toInsert: Player)

    fun getAllFavouritePlayers(): LiveData<List<FavPlayers>>

    suspend fun deleteFromPlayerDb(toDelete: FavPlayers)



}