package com.sithuhein.mm.roomtest.db

import com.sithuhein.mm.roomtest.db.entities.Director
import com.sithuhein.mm.roomtest.db.entities.School
import com.sithuhein.mm.roomtest.db.entities.Student
import com.sithuhein.mm.roomtest.db.entities.Subject
import com.sithuhein.mm.roomtest.db.relations.StudentSubjectCrossRef

object FakeDataSource {

    val directors = listOf<Director>(
        Director("Si Thu Hein", "Java School"),
        Director("Francis", "Kotlin School"),
        Director("Si Thu", "Python School"),
        Director("Phillip", "Python School"),
        Director("Dan Brown", "Python School"),
    )

    val schools = listOf<School>(
        School("Java School"),
        School("Kotlin School"),
        School("Python School"),
        School("Javascript School"),
        School("Swift School"),
    )

    val students = listOf<Student>(
        Student("Si Thu Hein", 4, "Kotlin School"),
        Student("Francis", 4, "Python School"),
        Student("Simon", 3, "Java School"),
        Student("David", 4, "Python School"),
        Student("Joseph", 4, "Python School"),
    )

    val subjects = listOf<Subject>(
        Subject("Kotlin"),
        Subject("Java"),
        Subject("Python"),
        Subject("Javascript"),
    )

    val studentSubjectRelations = listOf<StudentSubjectCrossRef>(
        StudentSubjectCrossRef("Francis", "Kotlin"),
        StudentSubjectCrossRef("Francis", "Java"),
        StudentSubjectCrossRef("Francis", "Python"),
        StudentSubjectCrossRef("Francis", "Javascript"),
        StudentSubjectCrossRef("Si Thu Hein", "Kotlin"),
        StudentSubjectCrossRef("David", "Kotlin"),
        StudentSubjectCrossRef("Simon", "Kotlin"),
    )
}