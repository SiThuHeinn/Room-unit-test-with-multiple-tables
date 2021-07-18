package com.sithuhein.mm.roomtest.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Director(
    @PrimaryKey(autoGenerate = false)
    val directorName : String,
    val schoolName : String
)