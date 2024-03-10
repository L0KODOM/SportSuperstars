package com.lokodom.sportsuperstars.data.model.teams

import com.squareup.moshi.Json

data class TeamDto(
    @field:Json(name = "idTeam")
    val id : String,
    @field:Json(name = "strTeam")
    val name : String,
    @field:Json(name = "strSport")
    val sport : String,
    @field:Json(name = "strLeague")
    val league : String,
    @field:Json(name = "strStadium")
    val stadium : String,
    @field:Json(name = "strTeamBadge")
    val badge : String,
    @field:Json(name = "idLeague")
    val leagueId : String
)
