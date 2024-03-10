package com.lokodom.sportsuperstars.ui.viewmodel.state

import com.lokodom.sportsuperstars.domain.model.Team

data class TeamsListState(
    val teamList: List<Team>? = null
)
