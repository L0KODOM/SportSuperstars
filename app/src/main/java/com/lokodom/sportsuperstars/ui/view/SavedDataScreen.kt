package com.lokodom.sportsuperstars.ui.view

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.sportsuperstars.ui.viewmodel.MainViewmodel

@Composable
fun SavedDataScreen (viewmodel: MainViewmodel, context: Context){

    val favPlayers by viewmodel.favPlayers.observeAsState(arrayListOf())
    val listSize = favPlayers.size

    Box(modifier = Modifier.fillMaxSize()){
        if (favPlayers.isNotEmpty()) {
            LazyColumn(content = {
                items(listSize) {
                    val player = favPlayers[it]
                    Card {
                        AsyncImage(
                            model = player.image, contentDescription = null,
                            contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = player.name)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = player.team)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = player.sport)
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { viewmodel.deleteFromDatabase(player) }) {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                        }
                    }
                }
            })
        }
    }
}