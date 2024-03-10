package com.lokodom.sportsuperstars.data.model.table

import com.squareup.moshi.Json

data class TablePositionDto(
    @field:Json(name = "strTeam")
    val name : String,
    @field:Json(name = "strTeamBadge")
    val thumb : String,
    @field:Json(name = "intWin")
    val wins : String,
    @field:Json(name = "intLoss")
    val losses : String,
    @field:Json(name = "intDraw")
    val draws : String,
    @field:Json(name = "intGoalsFor")
    val goalsF : String,
    @field:Json(name = "intGoalsAgainst")
    val goalsA : String,
    @field:Json(name = "intGoalsDifference")
    val goalsD : String,
    @field:Json(name = "intPoints")
    val points : String,
    @field:Json(name = "intRank")
    val position : String,

)
