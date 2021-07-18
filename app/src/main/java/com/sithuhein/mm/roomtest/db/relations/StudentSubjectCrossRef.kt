package com.sithuhein.mm.roomtest.db.relations

import androidx.room.Entity

@Entity(primaryKeys = ["studentName", "subjectName"])
class StudentSubjectCrossRef(
    val studentName : String,
    val subjectName : String
)