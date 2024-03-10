package com.lokodom.sportsuperstars.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokodom.sportsuperstars.data.database.entities.FavPlayers
import com.lokodom.sportsuperstars.domain.model.League
import com.lokodom.sportsuperstars.domain.model.Player
import com.lokodom.sportsuperstars.domain.useCases.getPlayersUseCase
import com.lokodom.sportsuperstars.domain.useCases.GetTableResultsUseCase
import com.lokodom.sportsuperstars.domain.useCases.GetTeamsUseCase
import com.lokodom.sportsuperstars.ui.viewmodel.state.LeaguesListState
import com.lokodom.sportsuperstars.ui.viewmodel.state.PlayerState
import com.lokodom.sportsuperstars.ui.viewmodel.state.TableResultsState
import com.lokodom.sportsuperstars.ui.viewmodel.state.TeamsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val playersUseCase: getPlayersUseCase,
    private val teamsUseCase: GetTeamsUseCase,
    private val tableResultsUseCase: GetTableResultsUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    var playerState by mutableStateOf(PlayerState())
        private set

    var leagueListState by mutableStateOf(LeaguesListState())
        private set

    var teamListState by mutableStateOf(TeamsListState())
        private set

    var tableResultsState by mutableStateOf(TableResultsState())
        private set

    val favPlayers : LiveData<List<FavPlayers>> by lazy { playersUseCase.getAllFavPlayers() }

    fun getPlayerByName(name:String, context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            if (!_isLoading.value) {
                _isLoading.value = true
                playerState = playerState.copy(player = playersUseCase.getPlayerByName(name, context))
                _isLoading.value = false
            }
        }
    }

    fun insertInDatabase(toInsert:Player){
        if (!_isLoading.value) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.value = true
                playersUseCase.insertInDatabase(toInsert)
                _isLoading.value = false

            }
        }
    }
    fun deleteFromDatabase(toDelete: FavPlayers){
        if (!_isLoading.value) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.value = true
                playersUseCase.deleteFromDatabase(toDelete)
                _isLoading.value = false

            }
        }
    }
    fun getLeaguesList(leagues: List<League>){
        if (!_isLoading.value) {
            viewModelScope.launch{
                _isLoading.value = true
                leagueListState = leagueListState.copy(leaguesList = leagues )
                _isLoading.value = false

            }
        }
    }
    fun getLeagueTeams(sport: String, country: String, context: Context, id: String){
        if (!_isLoading.value){
            viewModelScope.launch(Dispatchers.IO){
                _isLoading.value = true
                teamListState = teamListState.copy(
                    teamList = teamsUseCase.getLeagueTeams(sport, country, context, id))
                _isLoading.value = false

            }
        }
    }
    fun getTableResults(id: String, season: String, context: Context){
        if (!_isLoading.value){
            viewModelScope.launch(Dispatchers.IO){
                _isLoading.value = true
                tableResultsState = tableResultsState.copy(
                    rankTable = tableResultsUseCase.getTableResults(id,season, context))
                _isLoading.value = false

            }
        }
    }
}