package com.lokodom.sportsuperstars.domain.useCases

import android.content.Context
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

            Toast.makeText(context,"${e.message}",Toast.LENGTH_SHORT).show()

        }
        return favList
    }
}