package com.sithuhein.mm.roomtest.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.sithuhein.mm.roomtest.db.entities.Student
import com.sithuhein.mm.roomtest.db.entities.Subject

data class StudentWithSubjects(
    @Embedded val student : Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects : List<Subject>
)