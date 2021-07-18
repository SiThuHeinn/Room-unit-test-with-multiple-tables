package com.sithuhein.mm.roomtest.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.sithuhein.mm.roomtest.db.entities.School
import com.sithuhein.mm.roomtest.db.entities.Student


data class SchoolWithStudents(
    @Embedded
    val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>
)