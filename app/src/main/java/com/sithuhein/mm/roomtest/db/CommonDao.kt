package com.sithuhein.mm.roomtest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sithuhein.mm.roomtest.db.entities.Director
import com.sithuhein.mm.roomtest.db.entities.School
import com.sithuhein.mm.roomtest.db.entities.Student
import com.sithuhein.mm.roomtest.db.entities.Subject
import com.sithuhein.mm.roomtest.db.relations.*

@Dao
interface CommonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school : School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director : Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(studentSubjectCrossRef: StudentSubjectCrossRef)

    @Query("SELECT * FROM school")
    suspend fun getSchools() : List<School>

    @Query("SELECT * FROM director")
    suspend fun getDirectors() : List<Director>

    @Query("SELECT * FROM student")
    suspend fun getStudents() : List<Student>

    @Query("SELECT * FROM school WHERE schoolName = :schoolName ")
    suspend fun fetchSchoolAndDirectorWithSchoolName(schoolName : String) : List<SchoolAndDirector>

    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun fetchSchoolAndStudentsWithSchoolName(schoolName : String) : List<SchoolWithStudents>

    @Query("SELECT * FROM subject WHERE subjectName = :subjectName")
    suspend fun fetchStudentsOfSubject(subjectName : String) : List<SubjectWithStudents>

    @Query("SELECT * FROM student WHERE studentName = :studentName")
    suspend fun fetchSubjectsOfStudent(studentName : String) : List<StudentWithSubjects>


}