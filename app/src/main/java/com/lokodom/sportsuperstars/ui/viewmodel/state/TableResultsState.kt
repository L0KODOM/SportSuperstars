package com.lokodom.sportsuperstars.ui.viewmodel.state

import com.lokodom.sportsuperstars.domain.model.TablePosition

data class TableResultsState (
    val rankTable: List<TablePosition>? = null
)