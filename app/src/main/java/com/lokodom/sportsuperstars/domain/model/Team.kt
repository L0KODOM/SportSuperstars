package com.lokodom.sportsuperstars.domain.model

data class Team(
    val id: String,
    val name: String,
    val sport: String,
    val league: String,
    val stadium: String,
    val badge: String,
    val country: String,
    val leagueId: String
)
