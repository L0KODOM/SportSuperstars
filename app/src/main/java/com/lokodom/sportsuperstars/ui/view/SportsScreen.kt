package com.lokodom.sportsuperstars.ui.view

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.sportsuperstars.domain.model.League
import com.lokodom.sportsuperstars.domain.model.TablePosition
import com.lokodom.sportsuperstars.domain.model.Team
import com.lokodom.sportsuperstars.domain.model.sportsList
import com.lokodom.sportsuperstars.ui.viewmodel.MainViewmodel

@Composable
fun SportsScreen(viewmodel: MainViewmodel, context: Context){
    
    var leaguesExpanded by remember{ mutableStateOf(false) }
    var teamsExpanded by remember{ mutableStateOf(false) }
    var tableExpanded by remember{ mutableStateOf(false) }

    var loading by remember{ mutableStateOf(false) }

    LaunchedEffect(viewmodel) {
        viewmodel.isLoading.collect { isLoading ->
            loading = isLoading
        }
    }
    if (loading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }else{
        Box(modifier = Modifier.fillMaxSize()){
            if (!leaguesExpanded && !teamsExpanded && !tableExpanded) {
                LazyColumn(content = {
                    items(sportsList) {
                        Row (modifier = Modifier.clickable {
                            viewmodel.getLeaguesList(it.leagues)
                            leaguesExpanded = true
                        }){
                            Text(text = it.name)
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier.size(50.dp)){
                                AsyncImage(model = it.image, contentDescription = null,
                                    contentScale = ContentScale.FillBounds)
                            }
                        }
                    }
                })
            }
            if (leaguesExpanded && !teamsExpanded && !tableExpanded){
                LeaguesColumn(data = viewmodel.leagueListState.leaguesList,
                    context,
                    viewmodel,
                    { teamsExpanded = true },
                    { tableExpanded = true },
                    {leaguesExpanded = false}
                )
            }
            if (teamsExpanded && !tableExpanded){
                TeamsColumn(data = viewmodel.teamListState.teamList){ teamsExpanded = false }
            }
            if (tableExpanded && !teamsExpanded){
                RankTable(data = viewmodel.tableResultsState.rankTable){ tableExpanded = false }
            }
        }
    }
}

@Composable
fun LeaguesColumn(data: List<League>?, context: Context,
                  viewmodel: MainViewmodel, teamsExpanded: ()->Unit,
                  tableExpanded: ()->Unit, close: ()-> Unit){

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                horizontalArrangement = Arrangement.End){
                Icon(imageVector = Icons.Filled.Close, contentDescription = null,
                    modifier = Modifier.clickable { close() })
            }
            if (data != null){
                LazyColumn(content = {
                    items(data){
                        Row {
                            Text(text = it.name)
                            Spacer(modifier = Modifier.width(10.dp))
                            AsyncImage(model = it.image, contentDescription = null)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Button(onClick = { viewmodel.getTableResults(it.id,"2020-2021", context)
                                tableExpanded() }) {
                                Text(text = "Table")
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(onClick = { viewmodel.getLeagueTeams(it.sport,it.country, context, it.id)
                                teamsExpanded() }) {
                                Text(text = "Teams")
                            }
                        }
                    }
                })
            }
        }
    }
}

@Composable
fun TeamsColumn(data:List<Team>?, close: ()-> Unit){

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                horizontalArrangement = Arrangement.End){
                Icon(imageVector = Icons.Filled.Close, contentDescription = null,
                    modifier = Modifier.clickable { close() })
            }
            if (data != null){
                LazyColumn(content = {
                    items(data){
                        Row {
                            Text(text = it.name)
                            Spacer(modifier = Modifier.width(10.dp))
                            AsyncImage(model = it.badge, contentDescription = null)
                        }
                    }
                })
            }
        }
    }
}

@Composable
fun RankTable(data: List<TablePosition>?, close: ()-> Unit){

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                horizontalArrangement = Arrangement.End){
                Icon(imageVector = Icons.Filled.Close, contentDescription = null,
                    modifier = Modifier.clickable { close() })
            }
            if (data != null){
                LazyColumn(content = {
                    items(data){
                        Row {
                            AsyncImage(model = it.thumb, contentDescription = null)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.position)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.name)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.points)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.wins)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.draws)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.losses)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.goalsF)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.goalsA)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it.goalsD)
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                })
            }
        }
    }
}