package com.lokodom.sportsuperstars.data.model.players

import com.squareup.moshi.Json

data class PlayerDto(
    @field:Json(name = "strPlayer")
    val name: String,
    @field:Json(name = "strSport")
    val sport: String,
    @field:Json(name = "strTeam")
    val team: String,
    @field:Json(name = "dateBorn")
    val dateBorn: String?,
    @field:Json(name = "strBirthLocation")
    val birthPlace: String?,
    @field:Json(name = "strFanart1")
    val image: String?
)



