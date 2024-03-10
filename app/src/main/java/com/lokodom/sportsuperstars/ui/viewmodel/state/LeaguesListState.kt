package com.lokodom.sportsuperstars.ui.viewmodel.state

import com.lokodom.sportsuperstars.domain.model.League

data class LeaguesListState(
    val leaguesList: List<League>? = null
)
