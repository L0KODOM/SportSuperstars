package com.lokodom.sportsuperstars.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favPlayers")
data class FavPlayers(
    @ColumnInfo(name = "name")val name : String,
    @ColumnInfo(name = "sport")val sport : String,
    @ColumnInfo(name = "team")val team : String,
    @ColumnInfo(name = "birthDate")val birthDate : String?,
    @ColumnInfo(name = "birthPlace")val birthPlace : String?,
    @ColumnInfo(name = "image")val image : String?,
    @PrimaryKey(autoGenerate = true)val id : Int = 0,

)
