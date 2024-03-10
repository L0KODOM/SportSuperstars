package com.lokodom.sportsuperstars.domain.useCases

import android.content.Context
import android.widget.Toast
import com.lokodom.sportsuperstars.data.repository.MainRepository
import com.lokodom.sportsuperstars.domain.model.Team
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    val repository: MainRepository
) {

    suspend fun getLeagueTeams(sport: String, country: String, context: Context, id: String): List<Team>{

        var teamList = emptyList<Team>()

        try {

            val allTeams = repository.getLeagueTeams(sport, country)
            teamList = allTeams.filter { it.leagueId == id }

        }catch (e:Exception){

            Toast.makeText(context,"${e.message}",Toast.LENGTH_SHORT).show()

        }
        println(teamList)
        return teamList

    }

}