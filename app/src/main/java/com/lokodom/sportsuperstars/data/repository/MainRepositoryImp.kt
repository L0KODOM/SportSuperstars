package com.lokodom.sportsuperstars.data.repository

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import com.lokodom.sportsuperstars.data.database.dao.PlayerDao
import com.lokodom.sportsuperstars.data.database.entities.FavPlayers
import com.lokodom.sportsuperstars.data.network.SportsApi
import com.lokodom.sportsuperstars.domain.model.Player
import com.lokodom.sportsuperstars.domain.model.TablePosition
import com.lokodom.sportsuperstars.domain.model.Team
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val sportsApi: SportsApi,
    private val playerDao: PlayerDao
): MainRepository{

    override suspend fun getSearchedPlayer(name: String): Player {
        val result = sportsApi.getPlayerByName(name)

        val playerName = result.player[0].name
        val sport = result.player[0].sport
        val team = result.player[0].team
        val date = result.player[0].dateBorn
        val place = result.player[0].birthPlace
        val image = result.player[0].image

        return Player(playerName, sport,team,date,place, image)
    }

    override suspend fun getLeagueTeams(sport: String, country: String): List<Team> {
        val listSize = sportsApi.getTeamsBySportAndCountry(sport, country).teams.size
        val finalList = mutableListOf<Team>()

        for(i in 0 until  listSize){
            val id = sportsApi.getTeamsBySportAndCountry(sport, country).teams[i].id
            val name = sportsApi.getTeamsBySportAndCountry(sport, country).teams[i].name
            val league = sportsApi.getTeamsBySportAndCountry(sport, country).teams[i].league
            val badge = sportsApi.getTeamsBySportAndCountry(sport, country).teams[i].badge
            val stadium = sportsApi.getTeamsBySportAndCountry(sport, country).teams[i].stadium
            val leagueId = sportsApi.getTeamsBySportAndCountry(sport, country).teams[i].leagueId

            val team = Team(id = id,name, sport, league, stadium, badge, country, leagueId)
            finalList.add(team)

        }
        return finalList
    }

    override suspend fun getTableResults(id: String, season: String): List<TablePosition> {

        val listSize = sportsApi.getTableResults(id, season).table.size
        val finalList = mutableListOf<TablePosition>()

        for (i in 0 until listSize){
            var goalsA= ""
            var goalsF= ""
            var goalsD= ""

            val name = sportsApi.getTableResults(id, season).table[i].name
            val thumb = sportsApi.getTableResults(id, season).table[i].thumb
            val wins = sportsApi.getTableResults(id, season).table[i].wins
            val losses = sportsApi.getTableResults(id, season).table[i].losses
            val draws = sportsApi.getTableResults(id, season).table[i].draws
            if (sportsApi.getTableResults(id, season).table[i].goalsF != null){
                goalsF = sportsApi.getTableResults(id, season).table[i].goalsF.toString()
            }
            if (sportsApi.getTableResults(id, season).table[i].goalsA != null){
                goalsA = sportsApi.getTableResults(id, season).table[i].goalsA.toString()
            }
            if (sportsApi.getTableResults(id, season).table[i].goalsD != null){
                goalsD = sportsApi.getTableResults(id, season).table[i].goalsD.toString()
            }
            val points = sportsApi.getTableResults(id, season).table[i].points
            val position = sportsApi.getTableResults(id, season).table[i].position

            val tablePosition = TablePosition(name, thumb, wins, losses, draws, goalsF, goalsA, goalsD, points, position)
            finalList.add(tablePosition)
        }
        return finalList
    }

    override suspend fun insertInPlayerDb(toInsert: Player) {

        val name = toInsert.name
        val sport = toInsert.sport
        val team = toInsert.team
        val birthDate = toInsert.birthDate
        val birthPlace = toInsert.birthPlace
        val image = toInsert.image

        val favPlayer = FavPlayers(name,sport,team,birthDate,birthPlace, image)

        playerDao.insert(favPlayer)
    }

    override suspend fun deleteFromPlayerDb(toDelete: FavPlayers) {
        playerDao.delete(toDelete)
    }

    override fun getAllFavouritePlayers(): LiveData<List<FavPlayers>> {
        return playerDao.getAll()
    }
}