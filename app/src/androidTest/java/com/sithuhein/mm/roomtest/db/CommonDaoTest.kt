package com.sithuhein.mm.roomtest.db

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.sithuhein.mm.roomtest.db.entities.Director
import com.sithuhein.mm.roomtest.db.entities.School
import com.sithuhein.mm.roomtest.db.entities.Student
import com.sithuhein.mm.roomtest.db.entities.Subject
import com.sithuhein.mm.roomtest.db.relations.SchoolAndDirector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CommonDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: CommonDao

    private val TAG = "CommonDaoTest"

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.commonDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertSchool() = runBlockingTest {
        FakeDataSource.schools.forEach { school ->
            dao.insertSchool(school)
        }
        val mSchool = School("Kotlin School")
        val schools = dao.getSchools()
        schools.forEach {
            Log.d( TAG, it.schoolName)
        }
        assertThat(schools).contains(mSchool)
    }

    @Test
    fun insertDirector() = runBlockingTest {
        FakeDataSource.directors.forEach { director ->
            dao.insertDirector(director)
        }
        val mDirector = Director("Francis", "Kotlin School")
        val directors = dao.getDirectors()
        directors.forEach {
            Log.d( TAG, "${it.directorName} ${it.schoolName}")
        }
        assertThat(directors).contains(mDirector)
    }

    @Test
    fun insertStudent() = runBlockingTest {
        FakeDataSource.students.forEach { student -> dao.insertStudent(student) }
        val mStudent = Student("David", 4, "Python School")
        val students = dao.getStudents()
        assertThat(students).contains(mStudent)
    }

    //Test to fetch all school with director
    // ... One to One Database Relationship
    @Test
    fun fetchSchoolWithDirectorWithSchoolName() = runBlockingTest {
        FakeDataSource.schools.forEach { school -> dao.insertSchool(school) }
        FakeDataSource.directors.forEach { director -> dao.insertDirector(director) }

        val mSchoolWithDirector =
            SchoolAndDirector(School("Kotlin School"), Director("Francis", "Kotlin School"))

        val schoolAndDirector = dao.fetchSchoolAndDirectorWithSchoolName("Kotlin School")
        Log.d(TAG, "Director : ${schoolAndDirector[0].director.directorName}")
        assertThat(schoolAndDirector).contains(mSchoolWithDirector)
    }


    //Test to fetch all school with director
    // ... One to One Database Relationship
    @Test
    fun fetchDirectors() = runBlockingTest {
        FakeDataSource.directors.forEach { director -> dao.insertDirector(director) }
        val mDirector = Director("Francis", "Kotlin School")
        val directors = dao.getDirectors()
        assertThat(directors).contains(mDirector)
    }


    // Test to retrieve all the students from database
    @Test
    fun fetchStudents() = runBlockingTest {
        FakeDataSource.students.forEach { student -> dao.insertStudent(student) }
        val mStudent = Student("Francis", 4, "Python School")
        val students = dao.getStudents()
        students.forEach { Log.d(TAG, "Student ${it.studentName}/${it.semester}/${it.schoolName}")}
        assertThat(students).contains(mStudent)
    }


    // Test to retrieve students from Python School
    // ...One to N Database Relationship
    @Test
    fun fetchSchoolAndStudentsWithSchoolName () = runBlockingTest {
        FakeDataSource.schools.forEach { school -> dao.insertSchool(school) }
        FakeDataSource.students.forEach { student -> dao.insertStudent(student)  }
        val mPythonStudent = Student("David", 4, "Python School")
        val schoolAndStudents = dao.fetchSchoolAndStudentsWithSchoolName("Python School")
        val students = mutableListOf<Student>()
        schoolAndStudents.forEach {
            students.addAll(it.students)
        }
        students.forEach { student -> Log.d(TAG, "Student ${student.studentName}/${student.semester}/${student.schoolName}")}
        assertThat(students).contains(mPythonStudent)
    }

    @Test
    fun fetchSubjectsOfStudent() = runBlockingTest {
        FakeDataSource.students.forEach { student -> dao.insertStudent(student) }
        FakeDataSource.subjects.forEach { subject -> dao.insertSubject(subject) }
        FakeDataSource.studentSubjectRelations.forEach { stuSbuRelation -> dao.insertStudentSubjectCrossRef(stuSbuRelation) }
        val mSubject = Subject("Kotlin")
        val allSubjects = mutableListOf<Subject>()
        val subjectOfStudents = dao.fetchSubjectsOfStudent("Francis")
        subjectOfStudents.forEach {
            it.subjects.forEach { subject ->
                Log.d(TAG, "Name : ${it.student.studentName}/ Subject : ${subject.subjectName}")
                allSubjects.add(subject)
            }
        }
        assertThat(allSubjects).contains(mSubject)
    }

    @Test
    fun fetchStudentsOfSubject() = runBlockingTest {
        FakeDataSource.students.forEach { student -> dao.insertStudent(student) }
        FakeDataSource.subjects.forEach { subject -> dao.insertSubject(subject) }
        FakeDataSource.studentSubjectRelations.forEach { stuSbuRelation -> dao.insertStudentSubjectCrossRef(stuSbuRelation) }
        val mStudent = Student("Francis", 4, "Python School")
        val students = mutableListOf<Student>()
        val studentsOfSubject = dao.fetchStudentsOfSubject("Kotlin")
        studentsOfSubject.forEach {
            it.students.forEach { student ->
                Log.d(TAG, "Name : ${student.studentName} / Subject ${it.subject.subjectName} / School : ${student.schoolName}")
                students.add(student)
            }
        }
        assertThat(students).contains(mStudent)
    }

}