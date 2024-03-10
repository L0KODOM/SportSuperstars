package com.lokodom.sportsuperstars.domain.model

data class Player(
    val name:String,
    val sport: String,
    val team:String,
    val birthPlace: String?,
    val birthDate: String?,
    val image: String?
)
