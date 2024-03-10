package com.lokodom.sportsuperstars.domain.useCases

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.lokodom.sportsuperstars.data.database.entities.FavPlayers
import com.lokodom.sportsuperstars.data.repository.MainRepository
import com.lokodom.sportsuperstars.domain.model.Player
import javax.inject.Inject

class getPlayersUseCase @Inject constructor(
    val repository: MainRepository
) {

    suspend fun getPlayerByName(name:String, context:Context): Player {

        var player: Player = Player("","","","","","")

        try {
            val searchPlayer= repository.getSearchedPlayer(name)
            player = searchPlayer
        }catch (e:Exception){
            Log.d("searchPlayer", "${e.message}")
            Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()
        }
        return player
    }

    suspend fun insertInDatabase(toInsert: Player){
        repository.insertInPlayerDb(toInsert)
    }

    suspend fun deleteFromDatabase(toDelete: FavPlayers){
        repository.deleteFromPlayerDb(toDelete)
    }

    fun getAllFavPlayers(): LiveData<List<FavPlayers>>{
        return repository.getAllFavouritePlayers()
    }

}