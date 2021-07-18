package com.sithuhein.mm.roomtest.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.sithuhein.mm.roomtest.db.entities.Student
import com.sithuhein.mm.roomtest.db.entities.Subject

class SubjectWithStudents (
    @Embedded val subject : Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students : List<Student>
)