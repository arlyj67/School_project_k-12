package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.Student
import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.model.StudentStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long>{
    fun findByStudentNumber(studentNumber: String): Student?
}