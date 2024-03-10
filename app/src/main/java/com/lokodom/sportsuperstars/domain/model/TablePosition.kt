package com.lokodom.sportsuperstars.domain.model

import com.squareup.moshi.Json

data class TablePosition(
    val name : String,
    val thumb : String,
    val wins : String,
    val losses : String,
    val draws : String,
    val goalsF : String,
    val goalsA : String,
    val goalsD : String,
    val points : String,
    val position : String,
)
