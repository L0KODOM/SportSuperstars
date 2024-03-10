package com.lokodom.sportsuperstars.data.network

import com.lokodom.sportsuperstars.data.model.players.PlayerDetailsDto
import com.lokodom.sportsuperstars.data.model.table.TableDto
import com.lokodom.sportsuperstars.data.model.teams.TeamsListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {

    @GET("searchplayers.php")
    suspend fun getPlayerByName(
        @Query("p") name: String
    ): PlayerDetailsDto

    @GET("search_all_teams.php")
    suspend fun getTeamsBySportAndCountry(
        @Query("s") sport: String,
        @Query("c") country: String
    ): TeamsListDto

    @GET("lookuptable.php")
    suspend fun getTableResults(
        @Query("l") id: String,
        @Query("s") season: String
    ): TableDto
}