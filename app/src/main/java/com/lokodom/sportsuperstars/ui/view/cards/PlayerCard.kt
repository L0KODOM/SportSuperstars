package com.lokodom.sportsuperstars.ui.view.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.sportsuperstars.ui.viewmodel.MainViewmodel
import com.lokodom.sportsuperstars.ui.viewmodel.state.PlayerState

@Composable
fun PlayerCard(data: PlayerState, viewmodel: MainViewmodel){
    
    if (data.player != null){
        Card {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Box(modifier = Modifier
                    .fillMaxWidth()){
                    if (data.player.image != null) {
                        AsyncImage(
                            model = data.player.image, contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Text(text = data.player.name)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = data.player.sport)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = data.player.team)
                Spacer(modifier = Modifier.height(10.dp))
                data.player.birthDate?.let { Text(text = it) }
                Spacer(modifier = Modifier.height(10.dp))
                data.player.birthPlace?.let { Text(text = it) }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { viewmodel.insertInDatabase(data.player) }) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null)
                }
            }
        }
    }
    
}