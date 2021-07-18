package com.sithuhein.mm.roomtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sithuhein.mm.roomtest.db.entities.Director
import com.sithuhein.mm.roomtest.db.entities.School
import com.sithuhein.mm.roomtest.db.entities.Student
import com.sithuhein.mm.roomtest.db.entities.Subject
import com.sithuhein.mm.roomtest.db.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Director::class,
        Student::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun commonDao(): CommonDao
}