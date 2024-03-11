package com.lokodom.sportsuperstars.domain.useCases

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.lokodom.sportsuperstars.data.repository.MainRepository
import com.lokodom.sportsuperstars.domain.model.TablePosition
import javax.inject.Inject

class GetTableResultsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun getTableResults(id: String, season: String, context: Context): List<TablePosition>{

        var favList = emptyList<TablePosition>()

        try {

            favList = repository.getTableResults(id, season)

        }catch (e:Exception){

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context,"not found",Toast.LENGTH_SHORT).show()
            }
            Log.d("table results", "${e.message}")
        }
        return favList
    }
}