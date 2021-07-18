package com.sithuhein.mm.roomtest.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.sithuhein.mm.roomtest.db.entities.Director
import com.sithuhein.mm.roomtest.db.entities.School


data class SchoolAndDirector(
    @Embedded val school : School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)