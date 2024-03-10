package com.lokodom.sportsuperstars.ui.view

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lokodom.sportsuperstars.ui.view.cards.PlayerCard
import com.lokodom.sportsuperstars.ui.viewmodel.MainViewmodel

@Composable
fun SearchScreen(viewModel: MainViewmodel, context: Context){

    var name by remember{ mutableStateOf("") }
    val scrollstate = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier.verticalScroll(scrollstate)){
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = name, onValueChange = {name = it} )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { viewModel.getPlayerByName(name, context) }) {
                Text(text = "play")
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (viewModel.playerState.player != null)
                PlayerCard(data = viewModel.playerState, viewModel)
        }

    }
}